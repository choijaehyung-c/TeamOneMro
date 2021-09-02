package mrone.teamone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mrone.mro.service.MroServiceEntrance;
import mrone.teamone.beans.OrderDetailBean;
import mrone.teamone.utill.Encryption;
import mrone.teamone.utill.ProjectUtils;

@Controller
@RequestMapping("/HSM")
public class OneControllerHSM {
	
	ModelAndView mav;
	@Autowired
	MroServiceEntrance mse;
	@Autowired
	private ProjectUtils pu;
	@Autowired
	Encryption enc;
	
	
	@PostMapping("/mroSupplyListForm")
	public ModelAndView mroSupplyListForm() {
		//System.out.println("공급사목록");
		mav = mse.SupplyListCtl();
		return mav;
	
	}
	
	@PostMapping("/mroClientListForm")
	public ModelAndView morClientListForm() {
		//System.out.println("고객사목록");
		mav = mse.ClientListCtl();
		return mav;
	}


}
