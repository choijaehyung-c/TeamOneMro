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
	
	public List<RequestOrderBean> getSupplyDealList(String re_spcode){
		return ssc.getSupplyDealListCtl(re_spcode);
	}
	
	public RequestOrderBean getSupplyDealDetail(String re_code) {
		return ssc.getSupplyDealDetailCtl(re_code);
	}
	
	public List<SupplySearchBean> getSearchSupplyDeal(String word){
		return ssc.getSearchSupplyDealCtl(word);
	}
	
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

	public List<RequestOrderBean> getTaxDill() {
		List<RequestOrderBean> reList = null;
		reList = ssc.getTaxDill();
		return reList;
	}

	public List<RequestOrderDetailBean> choiceDillInfoCtl(RequestOrderDetailBean rdb) {

		return ssc.choiceDillInfo(rdb);
	}

	public List<ProductBean> supplySearchProduct(ProductBean pd) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProductBean> supplyProductList(ProductBean pd) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProductBean> supplyGetCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RequestOrderBean> supplySearchAs(RequestOrderBean re) {
		// TODO Auto-generated method stub
		return null;
	}

	public String supplyResponseExchange(RequestOrderBean re) {
		// TODO Auto-generated method stub
		return null;
	}

	public String supplyResponseRefund(RequestOrderBean ro) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RequestOrderDetailBean> supplyReceiveAsDetail(RequestOrderBean ro) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RequestOrderBean> supplyReceiveExchangeListForm() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RequestOrderBean> supplyReceiveRefundListForm() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProductBean> getCate() {
		// TODO Auto-generated method stub
		return null;
	}

	public String supplyRequestDelete(ProductBean pb) {
		// TODO Auto-generated method stub
		return null;
	}

	public String supplyModifyStock(ProductBean pb) {
		// TODO Auto-generated method stub
		return null;
	}

	public String supplyRequestModify(ProductBean pb) {
		// TODO Auto-generated method stub
		return null;
	}

	public ProductBean supplyGetProductDetail(ProductBean pb) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
