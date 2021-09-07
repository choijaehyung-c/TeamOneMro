package mrone.teamone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrone.client.service.ClientServiceEntrance;
import mrone.supply.service.SupplyServiceEntranceCJH;
import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.ClientOrderBean;
import mrone.teamone.beans.ProductBean;
import mrone.teamone.beans.TaxBean;

@RestController
@RequestMapping("/cjh")
public class RestApiController1 {

	@Autowired
	private ClientServiceEntrance cse;
	@Autowired
	private SupplyServiceEntranceCJH ssec;
	
	@PostMapping("/clientOrder")
	public String clientOrderApi(@RequestBody ClientOrderBean co){
		System.out.println(co.getOs_clcode()+co.getOd().size());
		System.out.println("in Entrance");//cse.clientRequestOrder(co)
		return "한글";
	}
	
	@PostMapping("/clientRefund")
	public String clientRefundApi(@RequestBody ClientOrderBean co){	
		return cse.clientRequestRefund(co);
	}
	
	@PostMapping("/clientExchange")
	public String clientExchangeApi(@RequestBody ClientOrderBean co){
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
	
	@PostMapping("/getSupplyCateProductList")
	public List<ProductBean> getSupplyCateProductList(@RequestBody ProductBean pr){
		return ssec.getSupplyCateProductList(pr);
	}
	
	//@ModelAttribute
}
