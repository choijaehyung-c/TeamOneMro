package mrone.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.ClientOrderBean;
import mrone.teamone.beans.TaxBean;

@Service
public class ClientServiceEntrance {
	@Autowired
	ClientServiceCtl csc;
	
	public String clientRequestOrder(ClientOrderBean co) {
		co.setOs_state("PR");
		return csc.clientRequestCtl(co);
	}
	
	public String clientRequestRefund(ClientOrderBean co) {
		co.setOs_state("RR");
		return csc.clientRequestCtl(co);
	}
	
	public String clientRequestExchange(ClientOrderBean co) {
		co.setOs_state("ER");
		return csc.clientRequestCtl(co);
	}
	
	public List<TaxBean> clientGetTaxbill(ClientInfoBean ci) throws Exception {
		return csc.clientGetTaxbillListCtl(ci);
	}
	
	public TaxBean clientGetTaxbillDetail(ClientInfoBean ci) throws Exception {
		return csc.clientGetTaxbillDetailCtl(ci);
	}

}
