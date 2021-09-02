package mrone.teamone.controller;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;


import mrone.supply.service.SupplyServiceEntrance;
import mrone.teamone.auth.Authentication;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.utill.Encryption;


@Controller
@RequestMapping("/SupplyNSB")
public class OneControllerNSB {
	ModelAndView mav = null;
	@Autowired
	Authentication auth;
	@Autowired
	Encryption enc;
	@Autowired
	SupplyServiceEntrance sse; 
	
	
	@GetMapping("/supplyReceiveWaitOrderListForm")
	public ModelAndView supplyReceiveWaitOrderListForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("supplyhomeNSB");
		return mav;

	}
	
	@PostMapping("/getSupplyReceiveWaitOrderList")
	public List<RequestOrderBean> supplyReceiveWaitOrderList() {
		
		return sse.RequestWaitOrderListCtl();	
	}
}
