package mrone.teamone.controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	SupplyServiceEntrance sse = new SupplyServiceEntrance(); 
	
	
	@GetMapping("/supplyReceiveWaitOrderListForm")
	public ModelAndView supplyReceiveWaitOrderListForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("supplyhomeNSB");
		return mav;

	}
	
	@PostMapping("/getSupplyReceiveWaitOrderList")
	public ModelAndView supplyReceiveWaitOrderList(@ModelAttribute RequestOrderBean rb) {
		System.out.println(rb.getRE_SPCODE());
		ModelAndView mav = new ModelAndView();
		mav = sse.RequestWaitOrderListCtl(rb);
		
		return mav;

	}
}
