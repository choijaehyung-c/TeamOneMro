package mrone.teamone.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mrone.supply.service.SupplyServiceCtlJES;

import mrone.teamone.auth.Authentication;
import mrone.teamone.beans.MroAccessBean;
import mrone.teamone.beans.ProductBean;
import mrone.teamone.utill.Encryption;
import mrone.teamone.utill.ProjectUtils;

@Controller
@RequestMapping("/jes")
public class OneControllerJES {
	ModelAndView mav;
	@Autowired
	SupplyServiceCtlJES ssej;
	
	
	@PostMapping("/supplyProductListForm")
	public ModelAndView supplyProductListCtl(){
		//mav = ssej.supplyProductListCtl();
		return mav;
		
	}  
	
	@PostMapping("/SupplyNewRequeListForm")
	public ModelAndView NewSupplyPrListCtl() {
		//mav = ssej.NewSupplyPrListCtl();
		return mav;
	}
}