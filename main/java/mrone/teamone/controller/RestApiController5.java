package mrone.teamone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import mrone.supply.service.SupplyServiceEntrancJES;
import mrone.teamone.beans.ProductBean;

@RestController
@RequestMapping("/vue5")
public class RestApiController5 {
	@Autowired
	SupplyServiceEntrancJES ssej; 
	
	//상품리스트 상세보기
	@PostMapping("/productListDetail")
	public List<ProductBean> supplyProductListForm(){
		return ssej.supplyProductListCtl();	
	}
}
