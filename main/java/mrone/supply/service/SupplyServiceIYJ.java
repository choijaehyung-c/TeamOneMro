package mrone.supply.service;

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
	
	public List<MroOrderDetailBean> supplyReceiveRefundListForm() {
		mod= new MroOrderDetailBean();
		List<MroOrderDetailBean> list;
		
		mod.setOd_prspcode("KR001D");//Session이 없어서 강제로 넣어줌
		mod.setOs_state("RR");
		
		list = dao.getRefundListSp(mod);
		
		//System.out.println(dao.getRefundListSp(mod));
		
			
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
		
		mod.setOd_prspcode("KR001C"); //Session이 없어서 강제로 넣어줌(교환있는 회사)
		mod.setOs_state("ER");
		mav.setViewName("MroHome");
		mav.addObject("exchangeList",dao.getRefundListSp(mod));
		System.out.println(dao.getRefundListSp(mod));
		
		return mav;
	}
	
	public List<MroOrderDetailBean> supplyReceiveAsDetail(MroOrderBean mo){
		System.out.println(mo.getOs_code());
		List<MroOrderDetailBean> list;
		
		
		list = dao.supplyReceiveAsDetail(mo);
		return list;
	}

}
