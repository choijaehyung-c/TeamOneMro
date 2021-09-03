package mrone.supply.service;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import mrone.teamone.beans.MroOrderBean;
import mrone.teamone.beans.MroOrderDetailBean;
import mrone.teamone.utill.ProjectUtils;

@Service
public class SupplyServiceIYJ {

	private ModelAndView mav =null;
	
	@Autowired
	SupplyDaoIYJ dao;
	@Autowired
	private ProjectUtils pu;
	
	MroOrderDetailBean mod;
	
	public List<MroOrderBean> supplyReceiveRefundListForm() {
		mod= new MroOrderDetailBean();
		List<MroOrderBean> list;
		
		mod.setOd_prspcode("KR001D");//Session이 없어서 강제로 넣어줌
		mod.setOs_state("RR");
		
		list = dao.getRefundListSp(mod);
		
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getOs_state().equals("RR")) {
				list.get(i).setOs_state("반품요청");
			}
		}
		
		//System.out.println(dao.getRefundListSp(mod));
		
//Session			
//		try {//Session이 살아있으면, Session에 있는 아이디를 mod에 담는다.
//			if(pu.getAttribute("userSs")!=null) {
//				mod.setOd_prspcode((String)pu.getAttribute("userSs"));
//				dao.getRefundListSp(mod);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
				
		return list;
	}

	public ModelAndView supplyReceiveExchangeListForm() {
		mav = new ModelAndView();
		mod= new MroOrderDetailBean();
		List<MroOrderDetailBean> list;
		
		mod.setOd_prspcode("KR001D"); //Session이 없어서 강제로 넣어줌(교환있는 회사)
		mod.setOs_state("ER");
		mav.setViewName("MroHome");
		mav.addObject("exchangeList",dao.getRefundListSp(mod));
		//System.out.println(dao.getRefundListSp(mod));
		
		return mav;
	}
	
	//반품 또는 교환 디테일
	public List<MroOrderDetailBean> supplyReceiveAsDetail(MroOrderBean mo){
		List<MroOrderDetailBean> list;
				
		list = dao.supplyReceiveAsDetail(mo);
		
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getOd_stcode().equals("RR")) {
				list.get(i).setOd_stcode("반품요청");
			}else if(list.get(i).getOd_stcode().equals("OC")) {
				list.get(i).setOd_stcode("구매확정");
			}else if(list.get(i).getOd_stcode().equals("RC")){
				list.get(i).setOd_stcode("반품처리");
			}
		}
		System.out.println(list);
		return list;
	}

	//공급사 - 반품에 대한 응답(거절(FF)or수락(RC))
	public String supplyResponseRefund(MroOrderBean mo) {
		String message="";
		List<MroOrderDetailBean> ocList;
		
		System.out.println(mo);
		if(mo.getOs_state().equals("RC")) {
			System.out.println("수락");
			//원래 있었던 주문코드 RR->RC로 업데이트됐고
			if(dao.supplyResponseRefund(mo)) {
				//그 주문코드에 OC가 있다면 
				ocList = dao.supplyOCInfo(mo);
				System.out.println(ocList);
				for(int i=0; i<ocList.size(); i++) {		
					if(ocList.get(i).getOd_stcode().equals("OC")) {
						//새로운 주문코드를 만든다. 그 주문코드의 oc의 정보들을 넣기 od,os인설트
						SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
						Calendar cal = Calendar.getInstance();
						System.out.println("반품처리 완료");
						//mo.setOs_code();
					message="반품처리가 수락되었습니다.";
				}else {
					//System.out.println("새로운 주문서번호가없음");
					message="반품처리가 수락되었습니다.";
				}
				
				}
			}else {
				
			}
			
			
		}else {
			System.out.println("거절");
			//RR-> RF로 업데이트
			if(dao.supplyResponseRefund(mo)) {
				
			}
		}		
		return message;
	}


}
