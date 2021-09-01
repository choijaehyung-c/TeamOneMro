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
		List<ProductBean> productBean;
		productBean = sqlSession.selectList("supplyProductListCtl", pb);
		
		return productBean;
	}
	
	public String getProductDetail(ProductBean pb) {
		
		
		return null;
	}
	
	public List<ProductBean> NewSupplyPrListCtl(ProductBean pd){
		List<ProductBean> prBean;
		prBean = sqlSession.selectList("NewSupplyPrListCtl", pd);
		
		return prBean;
	}
	
	public String getSuReNewProduct(ProductBean pd) {
		return null;
	}
	
	public String supplyReModifyCtl (ProductBean pd) {
		
		return null;
	}
	
	
}
