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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mrone.client.service.ClientServiceEntrance;
import mrone.teamone.auth.Authentication;
import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.ClientOrderBean;
import mrone.teamone.beans.MroAccessBean;
import mrone.teamone.beans.TaxBean;
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

	
	
	@PostMapping("/clientOrder")
	public String clientOrderApi(@ModelAttribute ClientOrderBean co){
		return cse.clientRequestOrder(co);
	}
	
	@PostMapping("/clientRefund")
	public String clientRefundApi(@ModelAttribute ClientOrderBean co){	
		return cse.clientRequestRefund(co);
	}
	
	@PostMapping("/clientExchange")
	public String clientExchangeApi(@ModelAttribute ClientOrderBean co){
		return cse.clientRequestExchange(co);
	}
	
	@PostMapping("/clientGetTaxbill")
	public List<TaxBean> clientGetTaxbillApi(@ModelAttribute ClientInfoBean ci) throws Exception {
		return cse.clientGetTaxbill(ci);
	}
	
	@PostMapping("/clientGetTaxbillDetail")
	public TaxBean clientGetTaxbillDetailApi(@ModelAttribute ClientInfoBean ci) throws Exception {
		return cse.clientGetTaxbillDetail(ci);
	}
	
}
