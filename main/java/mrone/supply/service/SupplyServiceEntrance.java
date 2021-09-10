package mrone.supply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.DeliveryBean;
import mrone.teamone.beans.ProductBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;
import mrone.teamone.beans.SupplyInfoBean;
import mrone.teamone.beans.SupplySearchBean;

@Service
public class SupplyServiceEntrance {
	@Autowired
	SupplyServiceCtl ssc;
	
	public List<RequestOrderBean> getSupplyDealList(){
		return ssc.getSupplyDealListCtl();
	}
	
	public List<RequestOrderBean> getSupplyDealDetail(String re_code) {
		return ssc.getSupplyDealDetailCtl(re_code);
	}
	
	public List<SupplySearchBean> getSearchSupplyDeal(String word){
		return ssc.getSearchSupplyDealCtl(word);
	}
	////////////////////////////////////////////////////////////////////
	public List<ProductBean> getSupplyCateProductList(ProductBean pr){
		return ssc.getSupplyCateProductList(pr);
	}
	
	public List<RequestOrderBean> RequestWaitOrderListCtl() {			
		List<RequestOrderBean> reList = null;
		reList = ssc.waitOrderlist();
		return reList;
	}
	
	public List<RequestOrderBean> RequestClearOrderListCtl() {			
		List<RequestOrderBean> reList = null;
		reList = ssc.clearOrderlist();
		return reList;
	}
	
	
	public List<RequestOrderBean> RequestWaitOrderListCtlD(RequestOrderBean rb) {			
		
		
		return ssc.waitOrderlistD(rb);
	}
	
	public List<RequestOrderBean> RequestClearOrderListCtlD(RequestOrderBean rb) {			
		
		
		return ssc.clearOrderlistD(rb);
	}
	
	public String responseOrder(RequestOrderDetailBean rdb) {			
		String message = ssc.responseOrder(rdb);
		
		return message;
	}
	
	public List<DeliveryBean> getDLlist() {			
		List<DeliveryBean> reList = null;
		reList = ssc.getDLlist();
		return reList;
	}
	
	public String updateDL(DeliveryBean db) {			
		String message = ssc.updateDL(db);
		
		return message;
	}
	
	public List<ClientInfoBean> getTaxCL() {			
		List<ClientInfoBean> reList = null;
		reList = ssc.getTaxCL();
		return reList;
	}

	public List<ClientInfoBean> choiceCLInfoCtl(ClientInfoBean cb) {

		return ssc.choiceCLInfo(cb);
	}

	public List<SupplyInfoBean> choiceSPInfoCtl(SupplyInfoBean sb) {

		return ssc.choiceSPInfo(sb);
	}

	public List<RequestOrderBean> getTaxdeal() {
		List<RequestOrderBean> reList = null;
		reList = ssc.getTaxdeal();
		return reList;
	}

	public List<RequestOrderDetailBean> choicedealInfoCtl(RequestOrderDetailBean rdb) {

		return ssc.choicedealInfo(rdb);
	}

	public List<ProductBean> supplySearchProduct(ProductBean pd) {
		return ssc.supplySearchProduct(pd);
	}

	public List<ProductBean> supplyProductList(ProductBean pd) {
		
		return ssc.supplyProductList(pd);
	}

	public List<ProductBean> supplyGetCategory() {
		
		return ssc.supplyGetCategory();
	}

	public List<RequestOrderBean> supplySearchAs(RequestOrderBean re) {

		return ssc.supplySearchAs(re);
	}

	public String supplyResponseExchange(String re_code) {
		return ssc.supplyResponseExchange(re_code);
	}

	public String supplyResponseRefund(String re_code) {
		return ssc.supplyResponseRefund(re_code);
	}

	public List<RequestOrderDetailBean> supplyReceiveAsDetail(String re_code) {
		
		return ssc.supplyReceiveAsDetail(re_code);
	}
	
	public List<RequestOrderBean> supplyReceiveExchangeListForm() {//c
		return ssc.supplyReceiveExchangeListForm();
	}
	
	public List<RequestOrderBean> supplyReceiveRefundListForm() {//c
		return ssc.supplyReceiveRefundListForm();
	}

	public List<ProductBean> getCate() {

		return ssc.getCate();
	}

	public String supplyRequestDelete(ProductBean pb) {
		
		return ssc.supplyRequestDelete(pb);
	}

	public String supplyModifyStock(ProductBean pb) {

		return ssc.supplyModifyStock(pb);
	}

	public String supplyRequestModify(ProductBean pb) {
		
		return ssc.supplyRequestModify(pb);
	}

	public ProductBean supplyGetProductDetail(ProductBean pb) {
		
		return ssc.supplyGetProductDetail(pb);
	}
	
}
