package mrone.teamone.controller;







import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import mrone.supply.service.SupplyServiceEntranceNSB;
import mrone.teamone.auth.Authentication;
import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.DeliveryBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;
import mrone.teamone.beans.SupplyInfoBean;
import mrone.teamone.utill.Encryption;

@Controller
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
	
	@GetMapping("/issueTaxBillForm")
	public ModelAndView issueTaxBillForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("issueTaxBillForm");
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
	
	@PostMapping("/getTaxCL")
	public List<ClientInfoBean> getTaxCL() {
		List<ClientInfoBean> reList = null;
		reList = sse.getTaxCL();
	
		return reList;			
	}
	
	@PostMapping("/getchoiceCLInfo")
	public List<ClientInfoBean> getchoiceCLInfo(@RequestBody List<ClientInfoBean> list ) {
		System.out.println(sse.choiceCLInfoCtl(list.get(0)));
		return sse.choiceCLInfoCtl(list.get(0));	
	}
	
	@PostMapping("/getChoiceSPInfo")
	public List<SupplyInfoBean> getchoiceSPInfo(@RequestBody List<SupplyInfoBean> list ) {
		System.out.println(sse.choiceSPInfoCtl(list.get(0)));
		return sse.choiceSPInfoCtl(list.get(0));	
	}
	
	@PostMapping("/getTaxDill")
	public List<RequestOrderBean> getTaxDill() {
		List<RequestOrderBean> reList = null;
		reList = sse.getTaxDill();
		System.out.println(sse.getTaxDill());
		return reList;			
	}
	
	@PostMapping("/getchoiceDillInfo")
	public List<RequestOrderDetailBean> getchoiceDillInfo(@RequestBody List<RequestOrderDetailBean> list ) {
		System.out.println(sse.choiceDillInfoCtl(list.get(0)));
		return sse.choiceDillInfoCtl(list.get(0));	
	}
	
}
