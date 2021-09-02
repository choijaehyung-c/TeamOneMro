package mrone.supply.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import mrone.teamone.beans.RequestOrderBean;


@Service
public class SupplyServiceEntrance {
	@Autowired
	SupplyServiceCtlNSB sscn;

	
	
	
	public List<RequestOrderBean> RequestWaitOrderListCtl() {			
		List<RequestOrderBean> reList = null;
		reList = sscn.waitOrderlist();
		return reList;
	}
	
	public List<RequestOrderBean> RequestWaitOrderListCtlD(List<RequestOrderBean> rc) {			
		List<RequestOrderBean> reList = null;
		reList = sscn.waitOrderlistD(rc);
		return reList;
	}
}
