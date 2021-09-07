package mrone.supply.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.DeliveryBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;
import mrone.teamone.beans.SupplyInfoBean;


@Service
public class SupplyServiceEntranceNSB {
	@Autowired
	SupplyServiceCtlNSB sscn;

	
	
	
	public List<RequestOrderBean> RequestWaitOrderListCtl() {			
		List<RequestOrderBean> reList = null;
		reList = sscn.waitOrderlist();
		return reList;
	}
	
	public List<RequestOrderBean> RequestClearOrderListCtl() {			
		List<RequestOrderBean> reList = null;
		reList = sscn.clearOrderlist();
		return reList;
	}
	
	
	public List<RequestOrderBean> RequestWaitOrderListCtlD(RequestOrderBean rb) {			
		
		
		return sscn.waitOrderlistD(rb);
	}
	
	public List<RequestOrderBean> RequestClearOrderListCtlD(RequestOrderBean rb) {			
		
		
		return sscn.clearOrderlistD(rb);
	}
	
	public String responseOrder(RequestOrderDetailBean rdb) {			
		String message = sscn.responseOrder(rdb);
		
		return message;
	}
	
	public List<DeliveryBean> getDLlist() {			
		List<DeliveryBean> reList = null;
		reList = sscn.getDLlist();
		return reList;
	}
	
	public String updateDL(DeliveryBean db) {			
		String message = sscn.updateDL(db);
		
		return message;
	}
	
	public List<ClientInfoBean> getTaxCL() {			
		List<ClientInfoBean> reList = null;
		reList = sscn.getTaxCL();
		return reList;
	}
	
public List<ClientInfoBean> choiceCLInfoCtl(ClientInfoBean cb) {			
		
		
		return sscn.choiceCLInfo(cb);
	}

public List<SupplyInfoBean> choiceSPInfoCtl(SupplyInfoBean sb) {			

	return sscn.choiceSPInfo(sb);
}

public List<RequestOrderBean> getTaxDill() {			
	List<RequestOrderBean> reList = null;
	reList = sscn.getTaxDill();
	return reList;
}

public List<RequestOrderDetailBean> choiceDillInfoCtl(RequestOrderDetailBean rdb) {			

	return sscn.choiceDillInfo(rdb);
}

	
}
