package mrone.teamone.controller;







import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import mrone.supply.service.SupplyServiceEntrance;
import mrone.teamone.auth.Authentication;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;
import mrone.teamone.utill.Encryption;


@RestController
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
		List<RequestOrderBean> reList = null;
		reList = sse.RequestWaitOrderListCtl();
		System.out.println(sse.RequestWaitOrderListCtl());
		return reList;
		
		
	}
	
	@PostMapping("/getSupplyReceiveWaitOrderListD")
	public List<RequestOrderBean> supplyReceiveWaitOrderListD(@RequestBody List<RequestOrderBean> list ) {
		
		System.out.println("dd");
		return sse.RequestWaitOrderListCtlD(list.get(0));
		
	}
	
	@PostMapping("/responseOrder")
	public String responseOrder(@RequestBody List<RequestOrderDetailBean> list ) {
		String message = sse.responseOrder(list.get(0));
		
		return message;
		
	}
}
