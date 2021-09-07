package mrone.mro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrone.teamone.beans.ClientOrderBean;
import mrone.teamone.beans.RequestOrderBean;

@Service
public class MroServiceEntranceCJH {
	@Autowired
	MroServiceCtlCJH msccc;
	
	public String mroRequestOrder(RequestOrderBean ro) {
		return msccc.mroRequestCtl(ro, "OR");
	}
	
	public String mroRequestRefund(RequestOrderBean ro) {
		return msccc.mroRequestCtl(ro, "RR");
	}
	
	public String mroRequestExchange(RequestOrderBean ro) {
		return msccc.mroRequestCtl(ro, "ER");
	}

}
