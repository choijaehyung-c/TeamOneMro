package mrone.supply.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import mrone.teamone.beans.ProductBean;


@Service
public class SupplyServiceCtl2 {
	ModelAndView mav;
	@Autowired
	SqlSessionTemplate sqlSession;
	
	
	public List<ProductBean> supplyProductListCtl(ProductBean pb) {
		
		return null;
	}
	
	public String getProductDetail(ProductBean pb) {
		
		
		return null;
	}
	
	public List<ProductBean> NewSupplyPrListCtl(ProductBean pd){
	
		return null;
	}
	
	public String getSuReNewProduct(ProductBean pd) {
		return null;
	}
	
	public String supplyReModifyCtl (ProductBean pd) {
		
		return null;
	}
	
	
}
