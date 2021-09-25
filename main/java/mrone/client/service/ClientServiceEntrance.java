package mrone.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.ClientOrderBean;
import mrone.teamone.beans.ClientOrderDecide;
import mrone.teamone.beans.TaxBean;

@Service
public class ClientServiceEntrance {
	@Autowired
	ClientServiceCtl csc;
	
	public List<String> clientRequestOrder(ClientOrderBean co) {
		return csc.clientRequestCtl(co,"OR");
	}
	
	public List<String> clientRequestRefund(ClientOrderBean co) {
		return csc.clientRequestCtl(co,"RR");
	}
	
	public List<String> clientRequestExchange(ClientOrderBean co) {
		return csc.clientRequestCtl(co,"ER");
	}
	
	public String supplyRequestOrder(ClientOrderBean co) {
		return csc.supplyRequestCtl(co,"OA");
	}
	
	public List<TaxBean> clientGetTaxbill(ClientInfoBean ci) throws Exception {
		return csc.clientGetTaxbillListCtl(ci);
	}
	
	public TaxBean clientGetTaxbillDetail(ClientInfoBean ci) throws Exception {
		return csc.clientGetTaxbillDetailCtl(ci);
	}
	
	public String clientOrderProcess(ClientOrderBean co, String sp_code) {
		return csc.clientOrderProcess(co, sp_code);
	}

	public String updOrderDecide(ClientOrderDecide cd) {
		return csc.updOrderDecide(cd);
	}
}
