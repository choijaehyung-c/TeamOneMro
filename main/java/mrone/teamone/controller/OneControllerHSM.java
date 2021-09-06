package mrone.teamone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mrone.mro.service.MroServiceCtlHSM;
import mrone.mro.service.MroServiceEntrance;
import mrone.teamone.utill.Encryption;
import mrone.teamone.utill.ProjectUtils;

@Controller
@RequestMapping("/HSM")
public class OneControllerHSM {
	
	ModelAndView mav;
	@Autowired
	MroServiceEntrance mse;
	@Autowired
	ProjectUtils pu;
	@Autowired
	Encryption enc;
	@Autowired
	MroServiceCtlHSM msch;
	
	
	@PostMapping("/MroRegisterNewProductForm")
	public String mroRegisterNewProductForm() {
		return "mroRequestNewProduct";
	}

	@PostMapping("/MroManageModifyProductForm")
	public String mroManageModifyProductForm() {
		return "mroManageModifyProductForm";
	}
	
}
