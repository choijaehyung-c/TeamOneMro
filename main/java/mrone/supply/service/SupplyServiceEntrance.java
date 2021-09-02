package mrone.supply.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import mrone.teamone.beans.RequestOrderBean;

@Service
public class SupplyServiceEntrance {
	@Autowired
	SupplyServiceCtlNSB sscn;
	ModelAndView mav = null;
	
	public List<RequestOrderBean> RequestWaitOrderListCtl() {			

		return sscn.waitOrderlist();
	}
}
