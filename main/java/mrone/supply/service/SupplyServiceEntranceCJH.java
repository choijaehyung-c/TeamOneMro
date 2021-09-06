package mrone.supply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.SupplySearchBean;

@Service
public class SupplyServiceEntranceCJH {
	@Autowired
	SupplyServiceCtlCjh sscc;
	
	public List<RequestOrderBean> getSupplyDealList(String re_spcode){
		return sscc.getSupplyDealListCtl(re_spcode);
	}
	
	public RequestOrderBean getSupplyDealDetail(String re_code) {
		return sscc.getSupplyDealDetailCtl(re_code);
	}
	
	public List<SupplySearchBean> getSearchSupplyDeal(String word){
		return sscc.getSearchSupplyDealCtl(word);
	}
	
}
