package mrone.supply.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import mrone.teamone.beans.ProductBean;
import mrone.teamone.beans.RequestOrderBean;

@Service
public class SupplyServiceEntrance {
	@Autowired
	SupplyServiceCtlNSB sscn;
	@Autowired
	SupplyServiceCtlJES sscj;
	
	
	ModelAndView mav = null;
	
	public ModelAndView RequestWaitOrderListCtl(RequestOrderBean rb) {			
		
		mav = sscn.waitOrderlist(rb);
		return mav;
	}
}
