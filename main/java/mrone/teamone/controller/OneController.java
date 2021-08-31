package mrone.teamone.controller;

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

@Controller
public class OneController {
	ModelAndView mav;
	
	@Autowired
	Authentication auth;
	
	@RequestMapping(value = "/", method = {RequestMethod.POST,RequestMethod.GET} )
	public ModelAndView home(@CookieValue(value = "key", required = false) Cookie ck) {
		System.out.println("imhere");
		mav = auth.start(ck);
		return mav;
	}
	
	@PostMapping("/Access")
	public ModelAndView Access(@ModelAttribute MroAccessBean ma,HttpServletResponse response) {
		mav = auth.accessMroCtl(ma);
		return mav;
	}
	
	
	/*
	 * @GetMapping("/test") public ModelAndView test() { mav = new ModelAndView();
	 * mav.addObject("testdata",td.test2()); mav.setViewName("/test"); return mav; }
	 */
	
}
