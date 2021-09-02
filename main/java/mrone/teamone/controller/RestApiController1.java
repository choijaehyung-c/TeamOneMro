package mrone.teamone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrone.client.service.ClientServiceEntrance;
import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.ClientOrderBean;
import mrone.teamone.beans.TaxBean;

@RestController
@RequestMapping("/cjh")
public class RestApiController1 {

	@Autowired
	private ClientServiceEntrance cse;

	
	@PostMapping("/clientOrder")
	public String clientOrderApi(@ModelAttribute ClientOrderBean co){
		return cse.clientRequestOrder(co);
	}
	
	@PostMapping("/clientRefund")
	public String clientRefundApi(@ModelAttribute ClientOrderBean co){	
		return cse.clientRequestRefund(co);
	}
	
	@PostMapping("/clientExchange")
	public String clientExchangeApi(@ModelAttribute ClientOrderBean co){
		return cse.clientRequestExchange(co);
	}
	
	@PostMapping("/clientGetTaxbill")
	public List<TaxBean> clientGetTaxbillApi(@ModelAttribute ClientInfoBean ci) throws Exception {
		return cse.clientGetTaxbill(ci);
	}
	
	@PostMapping("/clientGetTaxbillDetail")
	public TaxBean clientGetTaxbillDetailApi(@ModelAttribute ClientInfoBean ci) throws Exception {
		return cse.clientGetTaxbillDetail(ci);
	}
	
	
}
