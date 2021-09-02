package mrone.teamone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrone.mro.service.MroServiceEntrance;
import mrone.teamone.beans.MroOrderBean;

@RestController
@RequestMapping("/vue2")
public class RestApiController2 {
	
	@Autowired
	MroServiceEntrance mse;
	
	@PostMapping("/mroOrderListForm")
	public List<MroOrderBean> mroOrderListForm(){
		System.out.println("Restcontroller진입");
		
		return mse.getWaitOrderListCtl();
		
	}
	
}
