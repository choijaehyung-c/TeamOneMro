package mrone.teamone.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

import mrone.teamone.auth.Authentication;
import mrone.teamone.beans.AccessHistoryBean;
import mrone.teamone.beans.MroAccessBean;
import mrone.teamone.utill.Encryption;
import mrone.teamone.utill.ProjectUtils;

@Controller
public class OneController {
	ModelAndView mav;
	@Autowired
	Authentication auth;
	@Autowired
	Encryption enc;
	
	@RequestMapping(value = "/", method = {RequestMethod.POST,RequestMethod.GET} )
	public ModelAndView home(@CookieValue(value = "keykey", required = false)Cookie ck,HttpServletResponse res) {
		mav = auth.start(ck);
		if(ck !=null)res.addCookie(ck);
		return mav;
	}
	
	@PostMapping("/AccessMro")
	public ModelAndView accessMro(@ModelAttribute AccessHistoryBean ah,HttpServletResponse res) {
		Cookie ck = new Cookie("keykey",null);
		mav = auth.accessMroCtl(ah,ck);
		if(ck.getValue()!=null) res.addCookie(ck);
		return mav;
	}
	
	@PostMapping("/AccessOutMro")
	public ModelAndView accessOutMro(@ModelAttribute AccessHistoryBean ah,@CookieValue(value = "keykey", required = false) Cookie ck,HttpServletResponse res) {
		mav = auth.accessOutMroCtl(ah,ck);
		res.addCookie(ck);
		return mav;
	}
	
	
	
	/*
	 * @GetMapping("/test") public ModelAndView test() { mav = new ModelAndView();
	 * mav.addObject("testdata",td.test2()); mav.setViewName("/test"); return mav; }
	 */
	
}
