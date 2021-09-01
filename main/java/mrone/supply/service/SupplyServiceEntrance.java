package mrone.supply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import mrone.teamone.beans.RequestOrderBean;

@Service
public class SupplyServiceEntrance {
	@Autowired
	SupplyServiceCtlNSB sscn;
	
	@Autowired
	SupplyDaoNSB dao;
	
	private ModelAndView mav = null;
	
	public ModelAndView RequestWaitOrderListCtl(RequestOrderBean rb) {	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("supplyService");
		System.out.println("ㅠ");
		List<RequestOrderBean> waitOrderlist;
		System.out.println("서비스");
		waitOrderlist = dao.getWaitOrderList(rb);
		
//		mav.addObject("waitOrderlist", sscn.waitOrderlist(rb));
//		System.out.println(sscn.waitOrderlist(rb).size());
		return mav;
	}
}
