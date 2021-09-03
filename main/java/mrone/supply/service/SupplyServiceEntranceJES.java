package mrone.supply.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import mrone.teamone.beans.ProductBean;
import mrone.teamone.beans.RequestOrderBean;

@Service
public class SupplyServiceEntranceJES {
	ModelAndView mav = null;
	@Autowired
	SupplyServiceCtlJES sscj;	

	//상품리스트
	public ModelAndView supplyProductListCtl() {
		mav = new ModelAndView();
		mav.setViewName("supplyJES");
		mav.addObject("allprlist",sscj.supplyProductListCtl());
		
		return mav;
	}
	//새상품 리스트
	public ModelAndView NewSupplyPrListCtl() {
		mav = new ModelAndView();
		mav.setViewName("supplyJES");
		mav.addObject("newprlist",sscj.NewSupplyPrListCtl());
		
		return mav;
	}
	
	public List<ProductBean> getProductListDetail(String prcode){
		return null ;	
	}
}
