package mrone.teamone.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OneController {
	//test
<<<<<<< HEAD
	//hsm2
=======
	//현상민테스트ㄴㅇ
	//commit ps
	//dsfsdfdsfss
>>>>>>> 98de4b5004eee418b5ff5930310cb019ed109476
	ModelAndView mav;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	/*
	 * @GetMapping("/test") public ModelAndView test() { mav = new ModelAndView();
	 * mav.addObject("testdata",td.test2()); mav.setViewName("/test"); return mav; }
	 */
	
}
