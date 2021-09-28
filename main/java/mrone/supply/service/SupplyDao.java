package mrone.supply.service;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.DeliveryBean;
import mrone.teamone.beans.DeliveryInsert;
import mrone.teamone.beans.OrderDetailBean;
import mrone.teamone.beans.ProductBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;
import mrone.teamone.beans.SupplyInfoBean;
import mrone.teamone.beans.SupplyResponse;
import mrone.teamone.beans.SupplySearchBean;
import mrone.teamone.beans.TaxBean;

@Repository
public class SupplyDao {
	@Autowired
	SqlSessionTemplate sql;
	/* test */
	   public List<DeliveryBean> deliveryTest(DeliveryBean db) {
		      return sql.selectList("deliveryTest", db);
		   }

		   public void insertsdcode(DeliveryBean db) {
		      sql.update("insertsdcode", db);
		      
		   }
			/* test */
	
	
	
	   List<RequestOrderBean> waitOrderlist(String spcode){

		      return sql.selectList("getSupplyWaitOrderList",spcode);
		   }

		   
		   List<RequestOrderBean> clearOrderlist(String spcode){
		      return sql.selectList("getSupplyClearOrderList",spcode);
		   }


		   List<RequestOrderDetailBean> waitOrderlistD(String recode){
		      List<RequestOrderDetailBean> reList = null;

		      reList = sql.selectList("getSupplyWaitOrderListD",recode);
		      return reList;
		   }

		   
		   List<RequestOrderDetailBean> clearOrderlistD(String recode){

		      return sql.selectList("getSupplyWaitOrderListD",recode);
		   }
		   
		   //수정1
		    List<RequestOrderBean> RefuseOrderlist(String spcode) {
		         
		         return sql.selectList("getRefuseOrderList",spcode);
		   }

		    //수정1
		    List<RequestOrderDetailBean> refuseOrderListD(String recode) {
		         
		         return sql.selectList("getSupplyRefuseOrderListD",recode);
		   }
		    
		   //수정1
		   boolean supplyGoDelivery(String recode) {
		         return this.convertToBoolean(sql.update("supplyGoDelivery", recode));
		   }
		   
		   //수정1
		   List<DeliveryBean> getTrackDeliveryList(String spcode) {
		      // TODO Auto-generated method stub
		      System.out.println(spcode);
		      return sql.selectList("getTrackDeliveryList", spcode);
		   }

		   //수정1
		   List<DeliveryBean> getTrackDL(String recode) {
		      
		      return sql.selectList("getTrackDL", recode);
		   }
	
	List<ProductBean> supplyAllProductList(String sp_code){
	      
	      return sql.selectList("supplyAllProductList", sp_code);
	   }
	
	public Boolean supplyRequestCancel(ProductBean pb) {
		return convertToBoolean(sql.delete("supplyRequestCancel", pb));
	}
	
	public List<ProductBean> supplyPRAFProductList(String sp_code) {

		return sql.selectList("supplyPRAFProductList", sp_code);
	}

	public List<ProductBean> supplyMRDRDAProductList(String sp_code) {

		return sql.selectList("supplyMRDRDAProductList", sp_code);
	}
	
	public void deleteMR(ProductBean pb) {
		sql.delete("deleteMR", pb);
	}
	
	public void deleteDR(ProductBean pb) {
		sql.delete("deleteDR", pb);
	} 

	boolean updRequest(SupplyResponse sr) {
		return this.convertToBoolean( sql.update("updRequest", sr));
	}

	boolean updRequestDetail(SupplyResponse sr) {
		return this.convertToBoolean( sql.update("updRequestDetail", sr));
	}

	boolean updOrder(SupplyResponse sr) {
		return this.convertToBoolean( sql.update("updOrder", sr));
	}

	boolean updOrderDetail(SupplyResponse sr) {
		return this.convertToBoolean( sql.update("updOrderDetail", sr));
	}

	String getInvolvedOscode(SupplyResponse sr) {
		return sql.selectOne("getInvolvedOscode",sr); 
	}

	boolean updReasonRD(RequestOrderDetailBean rd) {
		return this.convertToBoolean(sql.update("updReasonRD",rd));
	}

	boolean updReasonOD(RequestOrderDetailBean rd) {
		return this.convertToBoolean(sql.update("updReasonOD",rd));
	}

	String getOSOriginCode(String os_code) {
		return sql.selectOne("getOSOriginCode",os_code);
	}
	
	String getREOriginCode(String re_code) {
		return sql.selectOne("getREOriginCode",re_code);
	}

	List<RequestOrderDetailBean> getNewRDForRefund(String re_code){
		return sql.selectList("getNewRDForRefund", re_code);
	}

	List<OrderDetailBean> getNewODForRefund(String os_code){
		return sql.selectList("getNewODForRefund", os_code);
	}
	
	String getRegion(String os_code) {
		return sql.selectOne("getRegion",os_code);
	}

	String getCLForRefund(String re_code) {
		return sql.selectOne("getCLForRefund",re_code);
	}


	String getDriver() {
		return sql.selectOne("getDriver");
	}

	boolean insertFirstLC(String os_code) {
		return this.convertToBoolean(sql.insert("insertFirstLC", os_code));
	}

	boolean insertFirstDL(DeliveryInsert di) {
		return this.convertToBoolean(sql.insert("insertFirstDL",di));
	}

	String getRecentlyLC(String os_code) {
		return sql.selectOne("getRecentlyLC", os_code);
	}

	List<RequestOrderBean> getSupplyDealList(String spcode) {
		return sql.selectList("getSupplyDealList", spcode);
	}

	List<RequestOrderDetailBean> getSupplyDealDetail(String re_code) {
		return sql.selectList("getSupplyDealDetail", re_code);
	}

	List<SupplySearchBean> getSearchSupplyDeal(String word) {
		return sql.selectList("getSearchSupplyDeal", word);
	}
	
	boolean issueTax(TaxBean tb) {		
		return convertToBoolean(sql.insert("issueTax", tb));	
	}
	
	List<TaxBean> getIssuedTax(String spcode) {		
		return sql.selectList("getIssuedTax", spcode);
	}
	
	TaxBean getIssuedTaxDetail(String tbcode) {		
		return sql.selectOne("getIssuedTaxDetail", tbcode);
	}
	
	List<OrderDetailBean> getTaxProduct(String oscode) {
		return sql.selectList("getTaxProduct", oscode);
	}
	

	List<ProductBean> getSupplyCateProductList(ProductBean pb) {
		return sql.selectList("getSupplyCateProductList", pb);
	}

	ProductBean supplyGetProductDetail(ProductBean pb) {
		return sql.selectOne("supplyGetProductDetail", pb);
	}

	boolean supplyModifyStock(ProductBean pb) {

		return convertToBoolean(sql.update("supplyModifyStock", pb));
	}

	boolean supplyRequestModify(ProductBean pb) {

		return convertToBoolean(sql.insert("supplyRequestModify", pb));
	}

	boolean supplyRequestDelete(ProductBean pb) {
		return convertToBoolean(sql.insert("supplyRequestDelete", pb));
	}

	boolean MRCheck(ProductBean pb) {
		return convertToBoolean(sql.selectOne("MRCheck", pb));
	}

	boolean updateSupplyRequestModify(ProductBean pb) {

		return convertToBoolean(sql.update("updateSupplyRequestModify", pb));
	}

	List<ProductBean> getCate() {
		return sql.selectList("getCate");
	}

	boolean supplyRequestNewProduct(ProductBean pb) {
		return convertToBoolean(sql.insert("supplyRequestNewProduct", pb));
	}

	boolean convertToBoolean(int data) {
		return data > 0 ? true : false;
	}


	//supplyReceiveRefundListForm
	List<RequestOrderBean> getReceiveAsListSp(RequestOrderBean ro) {
		return sql.selectList("getReceiveAsListSp", ro);
	}

	List<RequestOrderDetailBean> supplyReceiveAsDetail(RequestOrderBean ro) {
		return sql.selectList("supplyReceiveAsDetail", ro);
	}

	List<String> getPrnameAndCount(RequestOrderBean re){
		return sql.selectList("getPrnameAndCount",re);
	}
	/*
	 * boolean supplyResponseRefund(RequestOrderBean re) { return
	 * this.convertToBoolean(sql.update("supplyResponseRefund", re)); }
	 */

	/*
	 * boolean supplyResponseRefundOS(RequestOrderBean re) { return
	 * this.convertToBoolean(sql.update("supplyResponseRefundOS", re)); }
	 */

	/*
	 * boolean supplyResponseRefundRD(RequestOrderBean re) {
	 * 
	 * return this.convertToBoolean(sql.update("supplyResponseRefundRD", re)); }
	 */

	/*
	 * boolean supplyResponseRefundRE(RequestOrderBean re) {
	 * 
	 * return this.convertToBoolean(sql.update("supplyResponseRefundRE", re)); }
	 */

	/*
	 * boolean supplyResponseExchangeOD(RequestOrderBean re) { return
	 * this.convertToBoolean(sql.update("supplyResponseExchangeOD", re)); }
	 */
	/*
	 * boolean supplyResponseExchangeOS(RequestOrderBean re) { return
	 * this.convertToBoolean(sql.update("supplyResponseExchangeOS", re)); }
	 */

	/*
	 * boolean supplyResponseExchageRD(RequestOrderBean re) { return
	 * this.convertToBoolean(sql.update("supplyResponseExchageRD", re)); }
	 */

	/*
	 * boolean supplyResponseExchageRE(RequestOrderBean re) { return
	 * this.convertToBoolean(sql.update("supplyResponseExchageRE", re)); }
	 */

	/*
	 * List<MroOrderDetailBean> selRequest(RequestOrderBean re) { return
	 * sql.selectList("selRequest", re);
	 * 
	 * }
	 */

	/*
	 * boolean insNewOrders(RequestOrderBean re) { return
	 * this.convertToBoolean(sql.insert("insNewOrders", re)); }
	 */

	/*
	 * boolean insNewOd(List<MroOrderDetailBean> mod) { int list = 0;
	 * 
	 * for (int i = 0; i < mod.size(); i++) { list = sql.insert("insNewOd",
	 * mod.get(i)); } return this.convertToBoolean(list); }
	 */

	/*
	 * boolean insNewRequest(RequestOrderBean re) { return
	 * this.convertToBoolean(sql.insert("insNewRequest", re)); }
	 */

	/*
	 * boolean insNewRd(List<MroOrderDetailBean> mod) { int list = 0;
	 * 
	 * for (int i = 0; i < mod.size(); i++) { list = sql.insert("insNewRd",
	 * mod.get(i)); } return this.convertToBoolean(list); }
	 */

	// 오늘날짜 몇번까지 만들어졌는지 확인
	/*
	 * String checkCount() {
	 * 
	 * int number;
	 * 
	 * if (sql.selectOne("getCount") != null) { number = sql.selectOne("getCount");
	 * } else { number = 0; } String result = (number) + "";
	 * 
	 * for (int add = result.length(); add < 5; add++) { result = "0" + result; }
	 * 
	 * return result; }
	 */

	List<RequestOrderBean> supplySearchAs(RequestOrderBean re) {

		return sql.selectList("supplySearchAs", re);
	}

	// 교환 배달요청
	/*
	 * boolean supplyAskDelivery(DeliveryBean db) { System.out.println("배달요청 : " +
	 * db); return this.convertToBoolean(sql.insert("supplyAskDelivery", db)); }
	 */

	// 수정해야함 배달 locate만들기
	/*
	 * boolean makeDeliveryLocate() { return
	 * this.convertToBoolean(sql.insert("makeDeliveryLocate")); }
	 */

	/*
	 * String maxLCcode() { return sql.selectOne("maxLCcode"); }
	 */

	// 공급사 카테고리 받아오는 메서드
	public List<ProductBean> supplyGetCategory() {
		return sql.selectList("supplyGetCategory");
	}

	public List<ProductBean> supplyProductList(ProductBean pd) {

		return sql.selectList("supplyProductList", pd);
	}

	public List<ProductBean> supplySearchProduct(ProductBean pd) {

		return sql.selectList("supplySearchProduct", pd);
	}

	boolean updateDL(DeliveryBean db) {
		return this.convertToBoolean(sql.insert("updateDL", db));
	}

	List<DeliveryBean> getDLlist() {
		List<DeliveryBean> reList = null;
		reList = sql.selectList("getDLlist");
		return reList;
	}

	List<ClientInfoBean> getTaxCL() {
		List<ClientInfoBean> reList = null;
		reList = sql.selectList("getTaxCL");
		return reList;
	}

	ClientInfoBean choiceCLInfo(String cl_code) {		
		return sql.selectOne("choiceCLInfo", cl_code);
	}

	SupplyInfoBean choiceSPInfo(String spcode) {
		return sql.selectOne("choiceSPInfo", spcode);
	}

	List<RequestOrderBean> getTaxdeal(String spcode) {		
		return sql.selectList("getTaxdeal", spcode);
	}

	List<RequestOrderDetailBean> choiceDillInfo(RequestOrderDetailBean rdb) {

		List<RequestOrderDetailBean> reList = null;
		rdb.setRd_recode(rdb.getRd_recode());

		reList = sql.selectList("choiceDillInfo", rdb);

		return reList;
	}
	
	//수정2
	List<RequestOrderDetailBean> getChart(String spcode) {

		return sql.selectList("getChart", spcode);
	}


}
