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

import mrone.teamone.auth.Authentication;
import mrone.teamone.beans.MroAccessBean;
import mrone.teamone.utill.Encryption;
import mrone.teamone.utill.ProjectUtils;

@Controller
public class OneController {
	ModelAndView mav;
	@Autowired
	Authentication auth;
	@Autowired
	private ProjectUtils pu;
	@Autowired
	Encryption enc;
	
	@RequestMapping(value = "/", method = {RequestMethod.POST,RequestMethod.GET} )
	public ModelAndView home(@CookieValue(value = "key", required = false) Cookie ck) {
		mav = auth.start(ck);
		return mav;
	}
	
	@PostMapping("/AccessMro")
	public ModelAndView accessMro(@ModelAttribute MroAccessBean ma,HttpServletResponse response) {
		mav = auth.accessMroCtl(ma);
		try {
			if(pu.getAttribute("ck").equals(ma.getAhm_code())) {
			Cookie cookie = new Cookie("key", enc.aesEncode(ma.getAhm_code(),"session"));
			cookie.setMaxAge(36000); // 쿠키 유효기간 설정 (초 단위)
			response.addCookie(cookie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@PostMapping("/AccessOutMro")
	public ModelAndView accessOutMro(@ModelAttribute MroAccessBean ma) {
		mav = auth.accessOutMroCtl(ma);
		return mav;
	}
	
	
	
	/*
	 * @GetMapping("/test") public ModelAndView test() { mav = new ModelAndView();
	 * mav.addObject("testdata",td.test2()); mav.setViewName("/test"); return mav; }
	 */
	
}
