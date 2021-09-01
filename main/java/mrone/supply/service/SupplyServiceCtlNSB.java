package mrone.supply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import mrone.teamone.beans.RequestOrderBean;

@Service
public class SupplyServiceCtlNSB {
	@Autowired
	SupplyDaoNSB dao;
	
	List<RequestOrderBean> waitOrderlist(RequestOrderBean rb) {
	
		List<RequestOrderBean> waitOrderlist;
		System.out.println("서비스");
		waitOrderlist = dao.getWaitOrderList(rb);
		
		
		return waitOrderlist;
	}
}
