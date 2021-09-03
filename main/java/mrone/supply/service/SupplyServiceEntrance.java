package mrone.supply.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;


@Service
public class SupplyServiceEntrance {
	@Autowired
	SupplyServiceCtlNSB sscn;

	
	
	
	public List<RequestOrderBean> RequestWaitOrderListCtl() {			
		List<RequestOrderBean> reList = null;
		reList = sscn.waitOrderlist();
		return reList;
	}
	
	public List<RequestOrderBean> RequestWaitOrderListCtlD(RequestOrderBean rb) {			
		
		
		return sscn.waitOrderlistD(rb);
	}
	
	public String responseOrder(RequestOrderDetailBean rdb) {			
		String message = sscn.responseOrder(rdb);
		
		return message;
	}
}
