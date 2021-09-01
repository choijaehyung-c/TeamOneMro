package mrone.mro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class MroServiceEntrance {

	@Autowired
	MroServiceCtlYJ mseIYJ;
	
	private ModelAndView mav = null;

	public ModelAndView SupplyListCtl() {
		mav = new ModelAndView();
		
		
		mav.setViewName("MroHome");
		mav.addObject("list",mseIYJ.SupplyList());
		
		return mav;
	}
	
	

}
