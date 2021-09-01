package mrone.teamone.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mrone.client.service.ClientServiceEntrance;
import mrone.teamone.auth.Authentication;
import mrone.teamone.beans.ClientOrderBean;
import mrone.teamone.beans.MroAccessBean;
import mrone.teamone.utill.Encryption;
import mrone.teamone.utill.ProjectUtils;

@Controller
@RequestMapping("/cjh")
public class OneControllerCJH {
	ModelAndView mav;
	@Autowired
	private ClientServiceEntrance cse;
	@Autowired
	private ProjectUtils pu;

	
	
	@PostMapping("/clientOrderApi")
	public String clientOrderApi(@ModelAttribute ClientOrderBean co) {
		co.setOS_STATE("PR");
		return cse.clientRequest(co);
	}
	
	@PostMapping("/clientRefundApi")
	public String clientRefundApi(@ModelAttribute ClientOrderBean co) {
		co.setOS_STATE("RR");
		return cse.clientRequest(co);
	}
	
	@PostMapping("/clientExchangeApi")
	public String clientExchangeApi(@ModelAttribute ClientOrderBean co) {
		co.setOS_STATE("ER");
		return cse.clientRequest(co);
	}
	
	
}
