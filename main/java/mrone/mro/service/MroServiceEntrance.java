package mrone.mro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class MroServiceEntrance {

	@Autowired
	MroServiceCtlYJ mseIYJ;
	
	
	
	private ModelAndView mav =null;

	public ModelAndView SupplyListCtl() {
		mav = new ModelAndView();	
		mav.setViewName("MroHome");
		mav.addObject("Slist",mseIYJ.SupplyList());
		
		return mav;
	}

	public ModelAndView ClientListCtl() {
		mav = new ModelAndView();
		mav.setViewName("MroHome");
		mav.addObject("Clist",mseIYJ.ClientList());
		
		return mav;
	}

	public ModelAndView getWaitOrderListCtl() {
		mav = new ModelAndView();
		
		mav.setViewName("MroHome");
		mav.addObject("WorderList",mseIYJ.getWaitOrderList());
		
		return mav;
	}


	
	

}
