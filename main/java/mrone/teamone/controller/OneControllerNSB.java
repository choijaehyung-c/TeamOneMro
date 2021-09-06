package mrone.teamone.controller;







import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import mrone.supply.service.SupplyServiceEntranceNSB;
import mrone.teamone.auth.Authentication;
import mrone.teamone.beans.DeliveryBean;
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
	SupplyServiceEntranceNSB sse; 
	
	
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
	
	@PostMapping("/getSupplyReceiveClearOrderList")
	public List<RequestOrderBean> supplyReceiveClearOrderList() {
		List<RequestOrderBean> reList = null;
		reList = sse.RequestClearOrderListCtl();
		System.out.println(sse.RequestClearOrderListCtl());
		return reList;	
	}
	
	@PostMapping("/getSupplyReceiveWaitOrderListD")
	public List<RequestOrderBean> supplyReceiveWaitOrderListD(@RequestBody List<RequestOrderBean> list ) {
		
		System.out.println(sse.RequestWaitOrderListCtlD(list.get(0)));
		return sse.RequestWaitOrderListCtlD(list.get(0));
		
	}
	
	@PostMapping("/getSupplyReceiveClearOrderListD")
	public List<RequestOrderBean> supplyReceiveClearOrderListD(@RequestBody List<RequestOrderBean> list ) {
		
		System.out.println(sse.RequestClearOrderListCtlD(list.get(0)));
		return sse.RequestClearOrderListCtlD(list.get(0));
		
	}
	
	@PostMapping("/responseOrder")
	public String responseOrder(@RequestBody List<RequestOrderDetailBean> list ) {
		String message = sse.responseOrder(list.get(0));
		
		return message;
		
	}
	
	@PostMapping("/getDLlist")
	public List<DeliveryBean> getDLList() {
		List<DeliveryBean> reList = null;
		reList = sse.getDLlist();
		System.out.println(sse.getDLlist());
		return reList;	
	}
	
	@PostMapping("/choiceDV")
	public String updateDL(@RequestBody List<DeliveryBean> list ) {
		String message = sse.updateDL(list.get(0));
		
		return message;
		
	}
	
}
