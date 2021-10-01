package mrone.supply.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.DeliveryBean;
import mrone.teamone.beans.DriverLocationBean;
import mrone.teamone.beans.ProductBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;
import mrone.teamone.beans.SupplyInfoBean;
import mrone.teamone.beans.SupplyResponse;
import mrone.teamone.beans.SupplySearchBean;
import mrone.teamone.beans.TaxBean;

@Service
public class SupplyServiceEntrance {
	@Autowired
	SupplyServiceCtl ssc;
	/* test */
	   public List<DeliveryBean> deliveryTest() {
		      
		      return ssc.deliveryTest();
		   }

		   public void insertsdcode(DeliveryBean db) {
			   ssc.insertsdcode(db);
		   }
	/* test */
	
	
	public List<ProductBean> supplyAllProductList(){
	      
	      return ssc.supplyAllProductList();
	   }
	
	public List<ProductBean> supplyPRAFProductList() {
		return ssc.supplyPRAFProductList();
	}
	
	public List<ProductBean> supplyMRDRDAProductList() {
		return ssc.supplyMRDRDAProductList();
	}
	
	public String supplyRequestCancel(ProductBean pb) {
		return ssc.supplyRequestCancel(pb);
	}
	
	public List<RequestOrderBean> getSupplyDealList(){
		return ssc.getSupplyDealListCtl();
	}
	
	public List<RequestOrderDetailBean> getSupplyDealDetail(String re_code) {
		return ssc.getSupplyDealDetailCtl(re_code);
	}
	
	public List<SupplySearchBean> getSearchSupplyDeal(String word){
		return ssc.getSearchSupplyDealCtl(word);
	}
	
	public String issueTax(TaxBean tb) {
		return ssc.issueTaxCtl(tb);
	}
	
	public List<TaxBean> getIssuedTax() {
		return ssc.getIssuedTaxCtl();
	}
	
	public TaxBean getIssuedTaxDetail(String tbcode) {
		return ssc.getIssuedTaxDetailCtl(tbcode);
	}
	
	public List<ProductBean> getSupplyCateProductList(ProductBean pr){
		return ssc.getSupplyCateProductList(pr);
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

	public ClientInfoBean choiceCLInfoCtl(String cl_code) {
		return ssc.choiceCLInfo(cl_code);
	}


	public SupplyInfoBean choiceSPInfoCtl() {
		return ssc.choiceSPInfo();
	}


	public List<RequestOrderBean> getTaxdeal() {
		return ssc.getTaxdeal();
	}

	public List<RequestOrderDetailBean> choiceDillInfoCtl(RequestOrderDetailBean rdb) {

		return ssc.choiceDillInfo(rdb);
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

	public List<ProductBean> supplyGetBK() {
		return ssc.supplyGetBK();
	}
	
	public List<RequestOrderBean> supplySearchAs(RequestOrderBean re) {
		return ssc.supplySearchAs(re);
	}

	public String supplyResponseRefund(RequestOrderBean ro) {
		return ssc.supplyResponseRefund(ro);
	}

	public List<RequestOrderDetailBean> supplyReceiveAsDetailRR(String re_code) {
		return ssc.supplyReceiveAsDetail(re_code,"RR");
	}
	
	public List<RequestOrderDetailBean> supplyReceiveAsDetailER(String re_code) {
		return ssc.supplyReceiveAsDetail(re_code,"ER");
	}
	
	public List<RequestOrderBean> supplyReceiveExchangeListForm() {//c
		return ssc.supplyReceiveExchangeListForm();
	}
	
	public String supplyResponseOrder(RequestOrderBean ro) {
		return ssc.supplyResponseCtl(ro);
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
	
	
	   //수정1
	   public List<RequestOrderBean> RequestWaitOrderListCtl() {         
	      List<RequestOrderBean> reList = null;
	      reList = ssc.waitOrderlist();
	      return reList;
	   }
	   
	   
	   
	   public List<RequestOrderBean> RequestClearOrderListCtl() {         
	      return ssc.clearOrderlist();
	   }
	   
	   
	   public List<RequestOrderDetailBean> RequestWaitOrderListCtlD(String recode) {                     
	      return ssc.waitOrderlistD(recode);
	   }
	   
	   
	   public List<RequestOrderDetailBean> RequestClearOrderListCtlD(String recode) {                     
	      return ssc.clearOrderlistD(recode);
	   }
	   //수정1
	   public List<RequestOrderBean> getSupplyRefuseOrderList() {
	      return ssc.getSupplyRefuseOrderList();
	   }
	   
	   //수정1
	   public List<RequestOrderDetailBean> getSupplyRefuseOrderListDetail(String recode) {

	      return ssc.getSupplyRefuseOrderListDetail(recode);
	   }
	      
	   //수정1
	   public String supplyGoDelivery(String recode) {
	      
	      return ssc.supplyGoDelivery(recode);
	   }
	   
	   //수정1
	   public List<DeliveryBean> getTrackDeliveryList() {
	      
	      return ssc.getTrackDeliveryList();
	   }
	   
	   //수정1
//	   public List<DeliveryBean> getTrackDL(String recode) {
//
//	      return ssc.getTrackDL(recode);
//	   }
	   
	   public List<RequestOrderDetailBean> getChart() {
		   List<RequestOrderDetailBean> list = ssc.getChart();
		   for(int i=0; i<list.size(); i++) {
			   list.get(i).setPr_name(list.get(i).getPr_name());
			   list.get(i).setRd_quantity(list.get(i).getRd_quantity());

		   }		  		 
		   return list;
	   }
<<<<<<< HEAD

	public String insertGPS(DriverLocationBean dlb) {
	
		return ssc.insertGPS(dlb);
	}
=======
	   
	   public String supplyRequestNewProduct(ProductBean pb,HttpServletRequest req) {
		   	return ssc.supplyRequestNewProduct(pb,req);
	   }
	   
>>>>>>> c91b05ebc12ec224ed57135c0ebbe491ce94a467
}
