package mrone.teamone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import mrone.supply.service.SupplyServiceCtl2;
import mrone.teamone.beans.ProductBean;

@RestController
@RequestMapping("/vue5")
public class RestApiController5 {
	ModelAndView mav;
	@Autowired
	SupplyServiceCtl2 ssc2;
	

	@PostMapping("/SupplyProductListForm")
	public ModelAndView supplyProductListForm() {
		return mav;
	}
	
	@PostMapping("/productBean")
	public List<ProductBean> supplyProductListCtl(@RequestBody List<ProductBean> SupplyPrList){
		return ssc2.supplyProductListCtl(SupplyPrList.get(0));
		
	}

}
