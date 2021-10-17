package mrone.teamone.auth;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import mrone.teamone.beans.AccessBean;
import mrone.teamone.beans.AccessHistoryBean;
import mrone.teamone.utill.Encryption;
import mrone.teamone.utill.ProjectUtils;



@Service
public class Authentication {
	private ModelAndView mav = null;
	@Autowired
	private ProjectUtils pu;
	@Autowired
	Encryption enc;
	@Autowired
	AuthDao dao;
	
	public ModelAndView accessMroCtl(AccessHistoryBean ah,Cookie ck) {
		boolean tran = false;
		pu.setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,
				TransactionDefinition.ISOLATION_READ_COMMITTED, false);
		mav = new ModelAndView();
		try {
			//해당 아이디 로그인이 어딘가에서 된상태(db에 로그인상태)
			if(dao.getAccessHistorySum(ah)) {
				// 그 로그인이 다른브라우저에 되어 있을경우 강제종료후 로그인
				if(!(ah.getAh_browser()+ah.getAh_publicip()+ah.getAh_privateip()).equals(dao.getLastAccessInfo(ah))) {	 
					if(dao.forceLogout(ah)) {
						if(loginProcessMro(mav,ah,ck)) tran = true;
					}
				}else {
					//서버 로그인이 되어있지만 세션이 만료된 경우
					if(pu.getAttribute("userSs")==null){
						dao.forceLogout(ah);
						if(loginProcessMro(mav,ah,ck)) tran = true;
						
					//서버 로그인이 되어있고 세션이 살아 있는 경우(같은 브라우저,새탭 페이지안바뀐 로그인버튼)
					}else mav.setViewName("redirect:/");
				}
			//해당 아이디가 로그인이 안되어있을경우(db에 로그아웃상태)
			}else tran = loginProcessMro(mav,ah,ck);
		}catch (Exception e) {e.printStackTrace();}
		
		pu.setTransactionResult(tran);
		return mav;
	}

	private boolean loginProcessMro(ModelAndView mav,AccessHistoryBean ah,Cookie ck) {
		boolean tf = false;
		AccessBean ab = new AccessBean();
		//MRO 운영사 로그인
		if(ah.getAh_table().equals("AHM")){
			ab.setId(ah.getAh_code());
			ab.setPwd(ah.getAh_pwd());
			ab.setCol("MD_CODE");
			ab.setCol2("MD_PWD");
			ab.setTable("MRD");
		//MRO 공급사 로그인
		}else {
			ab.setId(ah.getAh_sdspcode()+ah.getAh_code());
			ab.setPwd(ah.getAh_pwd());
			ab.setCol("SD_SPCODE||SD_CODE");
			ab.setCol2("SD_PWD");
			ab.setTable("SD");
		}
		try {	
			if(pu.getAttribute("userSs")==null){
				if(dao.isUserId(ab)){
					ab.setPwd(enc.aesEncode(ab.getPwd(),ah.getAh_code()));
					if(dao.checkPwd(ab)){
						if(tf = dao.insAccessHistory(ah)) {
							mav.setViewName("redirect:/");
							if(ah.getAh_table().equals("AHM")) {
								ck.setValue("mro"+enc.aesEncode(ah.getAh_code(),"session"));
								pu.setAttribute("type","mro");
							}else {
								ck.setValue("sup"+enc.aesEncode(ah.getAh_code(),"session"));
								pu.setAttribute("type",enc.aesEncode(ah.getAh_sdspcode(),ah.getAh_code()));
							}
							ck.setMaxAge(60*60*12); // 쿠키 유효기간 설정 (초 단위) : 반나절
							pu.setAttribute("userSs",enc.aesEncode(ah.getAh_code(),"session"));
							pu.setAttribute("browser",enc.aesEncode(ah.getAh_browser()+ah.getAh_publicip()+ah.getAh_privateip(),"session"));
							tf = true;
						}
					}
				}
				if(!tf){
					mav.setViewName("accessForm");
					mav.addObject("message","alert('로그인실패');");					
				}
			}
		}catch (Exception e) {e.printStackTrace();}
		System.out.println(tf);
		return tf;
	}
	
	public ModelAndView accessOutMroCtl(AccessHistoryBean ah,Cookie ck) {
		if(ck != null)ah.setAh_table(ck.getValue().substring(0,3).equals("mro")?"AHM":"AHS");
		mav = new ModelAndView();
		try {
			if(pu.getAttribute("userSs") != null) {
				ah.setAh_code(enc.aesDecode((String)pu.getAttribute("userSs"),"session"));
				if(ah.getAh_table().equals("AHS")) ah.setAh_sdspcode(enc.aesDecode((String)pu.getAttribute("type"),ah.getAh_code()));
				if(dao.getLogOutAccessHistorySum(ah)) dao.insAccessHistory(ah);
				pu.removeAttribute("userSs");				
				pu.removeAttribute("browser");				
				pu.removeAttribute("type");
				mav.setViewName("redirect:/");
				mav.addObject("message","alert('로그아웃 되었습니다.');");
				System.out.println("로그아웃ctl성공");
			}else{
				mav.setViewName("redirect:/");
				mav.addObject("message","alert('이미 로그아웃 되었습니다.');");
			}
			ck.setMaxAge(0);//쿠키소멸
			ck.setValue(null);//쿠키소멸
		} catch (Exception e) {
			System.out.println("로그아웃 실패");
			e.printStackTrace();
		}
		return mav;
	}
	
	
	
	public ModelAndView start(Cookie ck) {
		mav = new ModelAndView();
		AccessHistoryBean ah = new AccessHistoryBean();
		// userSs 유저 세션
		if(ck != null) {
			ah.setAh_table(ck.getValue().substring(0,3).equals("mro")?"AHM":"AHS");
		try {
			//브라우저에 일단 세션이 남아 있는 경우
			if(pu.getAttribute("userSs")!=null){
				ah.setAh_code(enc.aesDecode((String)pu.getAttribute("userSs"),"session"));
				//남아 있는 세션이(해당아이디가) DB에 로그인 되어 있는상태 => 마이페이지로
				if(dao.getAccessHistorySum(ah) && dao.getLastAccessInfo(ah).equals(enc.aesDecode((String)pu.getAttribute("browser"),"session"))) {
					mav.setViewName(ah.getAh_table().equals("AHM")?"home":"supHome");
				//남아 있는 세션이(해당아이디가) DB에선 이미 로그아웃된경우 =>해당브라우저에 남아있던 세션도 죽임(꼭 새로고침 안해줘도됨 인터넷창 닫으면 어차피 세션 사라짐)
				}else{
					pu.removeAttribute("userSs");
					pu.removeAttribute("browser");
					pu.removeAttribute("type");
					mav.setViewName("accessForm");
					ck.setMaxAge(0);//쿠키소멸
					ck.setValue(null);//쿠키소멸
				}
			//브라우저에 해당사이트 할당된 세션이 없음
			}else {
				ck.setMaxAge(0);//쿠키소멸
				ck.setValue(null);//쿠키소멸
				mav.setViewName("accessForm");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}else {
		mav.setViewName("accessForm");
	}
		return mav;
	}

	
	
	public ModelAndView accessDelivery(AccessHistoryBean ah) {
		mav = new ModelAndView();
		String ENCPWD = null;
		if(dao.checkID(ah.getAh_code())) {
			try {
				ENCPWD = enc.aesDecode(dao.getDVpwd(ah.getAh_code()), ah.getAh_code());
				if(ah.getAh_pwd().equals(ENCPWD)) {
					mav.setViewName("delivery");
					pu.setAttribute("dvcode", ah.getAh_code());
					System.out.println("성공!!");
				}else {
					mav.setViewName("deliveryAccess");
					System.out.println(("아이디나 비밀번호를 확인해주세요3213123"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			mav.setViewName("deliveryAccess");
			System.out.println(("아이디나 비밀번호를 확인해주세요"));
		}
		
		return mav;
	}

	public ModelAndView deliveryLogOut() {
		mav = new ModelAndView();
		try {
			pu.removeAttribute("dvcode");
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("deliveryAccess");
		return mav;
	}
}
