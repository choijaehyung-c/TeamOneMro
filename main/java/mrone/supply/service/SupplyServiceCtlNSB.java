package mrone.supply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import mrone.teamone.beans.RequestOrderBean;

@Service
public class SupplyServiceCtlNSB {
	@Autowired
	SupplyDaoNSB dao;
	private ModelAndView mav = null;
	
	ModelAndView waitOrderlist(RequestOrderBean rb) {
		
		mav = new ModelAndView();
		mav.setViewName("supplyService");
		mav.addObject("waitOrderlist", dao.getWaitOrderList(rb));
		System.out.println(dao.getWaitOrderList(rb));
		
		return mav;
	}
	
}
