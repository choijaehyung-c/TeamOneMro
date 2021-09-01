package mrone.teamone.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import mrone.teamone.beans.AccessBean;
import mrone.teamone.beans.MroAccessBean;
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
	
	public ModelAndView accessMroCtl(MroAccessBean ma) {
		mav = new ModelAndView();
		try {
			//해당 아이디 로그인이 어딘가에서 된상태(db에 로그인상태)
			if(dao.getAccessHistorySum(ma)) {
				// 그 로그인이 다른브라우저에 되어 있을경우 강제종료후 로그인
				if(!(ma.getAHM_BROWSER()+ma.getAHM_PUBLICIP()+ma.getAHM_PRIVATEIP()).equals(dao.getLastAccessInfo(ma))) {	 
					if(dao.forceLogout(ma)) {
						System.out.print("강제로그아웃성공");
						loginProcess(mav,ma);
						System.out.println("다른브라우저로그인성공");
					}
				}else {
					if(pu.getAttribute("userSs")==null){//서버 로그인이 되어있지만 세션이 만료된 경우
						dao.forceLogout(ma);
						loginProcess(mav,ma);
					}else {//서버 로그인이 되어있고 세션이 살아 있는 경우(같은 브라우저,새탭 페이지안바뀐 로그인버튼)
						mav.setViewName("redirect:/");
					}
				}
			//해당 아이디가 로그인이 안되어있을경우(db에 로그아웃상태)
			}else{
				System.out.println("222");
				loginProcess(mav,ma);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	private void loginProcess(ModelAndView mav,MroAccessBean ma) {
		AccessBean ab = new AccessBean();
		ab.setId(ma.getAHM_CODE());
		ab.setPwd(ma.getMD_PWD());
		
		try {	
			if(pu.getAttribute("userSs")==null){
				boolean tf = false;
				if(dao.isUserId(ab)){
					System.out.println("아이디검증성공");
					ab.setPwd(enc.aesEncode(ab.getPwd(), ab.getId()));
					
					if(dao.checkPwd(ab)){
						System.out.println("로그인성공");
						if(tf = dao.insAccessHistory(ma)) {
							System.out.println("기록성공");
							mav.setViewName("redirect:/");
							pu.setAttribute("userSs",enc.aesEncode(ab.getId(),"session"));
							pu.setAttribute("ck",ab.getId());
						}
					}
				}
				if(!tf){
					mav.setViewName("access");
					mav.addObject("message","alert('로그인실패');");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ModelAndView accessOutMroCtl(MroAccessBean ma) {
		mav = new ModelAndView();
		try {
			if(pu.getAttribute("userSs") != null) {
				ma.setAHM_CODE(enc.aesDecode((String)pu.getAttribute("userSs"),"session"));
				if(dao.getLogOutAccessHistorySum(ma)) {
					dao.insAccessHistory(ma);
				}
				pu.removeAttribute("userSs");
				//쿠키 지우는거
				pu.removeAttribute("ck");
				mav.setViewName("redirect:/");
				mav.addObject("message","alert('로그아웃 되었습니다.');");
				System.out.println("로그아웃ctl성공");
			}else{
				mav.setViewName("redirect:/");
				mav.addObject("message","alert('이미 로그아웃 되었습니다.');");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	
	
	public ModelAndView start(Cookie ck) {
		mav = new ModelAndView();
		MroAccessBean ma = new MroAccessBean();
		// userSs 유저 세션
		try {
			//브라우저에 일단 세션이 남아 있는 경우
			if(pu.getAttribute("userSs")!=null){
				ma.setAHM_CODE(enc.aesDecode((String)pu.getAttribute("userSs"),"session"));
				//남아 있는 세션이(해당아이디가) DB에 로그인 되어 있는상태 => 마이페이지로
				if(dao.getAccessHistorySum(ma) && ck.getValue().equals((String)pu.getAttribute("userSs"))) {
					mav.setViewName("home");
				//남아 있는 세션이(해당아이디가) DB에선 이미 로그아웃된경우 =>해당브라우저에 남아있던 세션도 죽임(꼭 새로고침 안해줘도됨 인터넷창 닫으면 어차피 세션 사라짐)
				}else{
					//쿠키도삭제
					System.out.println("asd");
					pu.removeAttribute("userSs");
					mav.setViewName("accessForm");
				}
			//브라우저에 해당사이트 할당된 세션이 없음
			}else {
				mav.setViewName("accessForm");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	
	
	/*
	 * Cookie cookie = new Cookie("key", "hihicookie"); cookie.setMaxAge(3600); //
	 * 쿠키 유효기간 설정 (초 단위) response.addCookie(cookie); try {
	 * ss.setAttribute("session", "hihisession"); } catch (Exception e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 */
	
	

}
