package mrone.supply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrone.teamone.beans.ProductBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.SupplySearchBean;


@Service
class SupplyServiceCtlCjh {
	@Autowired
	SupplyDaoCJH dao;
	
	
	List<RequestOrderBean> getSupplyDealListCtl(String re_spcode){
		return dao.getSupplyDealList(re_spcode);
	}
	
	RequestOrderBean getSupplyDealDetailCtl(String re_code) {
		return dao.getSupplyDealDetail(re_code);
	}
	
	List<SupplySearchBean> getSearchSupplyDealCtl(String word){
		return dao.getSearchSupplyDeal(word);
	}
	
	List<ProductBean> getSupplyCateProductList(ProductBean pb){
		return dao.getSupplyCateProductList(pb);
	}
	

}
