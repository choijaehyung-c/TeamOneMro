package mrone.supply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

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
	
	public ModelAndView supplyReceiveRefundListForm() {
		mav = new ModelAndView();
		mod= new MroOrderDetailBean();
		List<MroOrderDetailBean> list;
		
		mod.setOd_prspcode("KR001D");
		
		mav.setViewName("MroHome");
		mav.addObject("refundList",dao.getRefundListSp(mod));
		System.out.println(dao.getRefundListSp(mod));
		//System.out.println(mod);
		
		
//		try {//Session이 살아있으면, Session에 있는 아이디를 mod에 담는다.
//			if(pu.getAttribute("userSs")!=null) {
//				mod.setOd_prspcode((String)pu.getAttribute("userSs"));
//				dao.getRefundListSp(mod);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
		
		return mav;
	}

}
