package mrone.supply.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.DeliveryBean;
import mrone.teamone.beans.MroOrderDetailBean;
import mrone.teamone.beans.OrderDetailBean;
import mrone.teamone.beans.ProductBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;
import mrone.teamone.beans.SupplyInfoBean;
import mrone.teamone.beans.SupplyResponse;
import mrone.teamone.beans.SupplySearchBean;

@Repository
public class SupplyDao {
	@Autowired
	SqlSessionTemplate sql;
	
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
	
	List<RequestOrderDetailBean> getNewRDForRefund(String re_code){
		return sql.selectList("getNewRDForRefund", re_code);
	}
	
	List<OrderDetailBean> getNewODForRefund(String os_code){
		return sql.selectList("getNewODForRefund", os_code);
	}
	
	String getCLForRefund(String re_code) {
		return sql.selectOne("getCLForRefund",re_code);
	}
	
	List<RequestOrderBean> getSupplyDealList(String re_spcode) {
		return sql.selectList("getSupplyDealList", re_spcode);
	}

	RequestOrderBean getSupplyDealDetail(String re_code) {
		return sql.selectOne("getSupplyDealDetail", re_code);
	}

	List<SupplySearchBean> getSearchSupplyDeal(String word) {
		return sql.selectList("getSearchSupplyDeal", word);
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

	List<RequestOrderDetailBean> supplyReceiveAsDetail(String re_code) {
		return sql.selectList("supplyReceiveAsDetail", re_code);
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
	
	boolean responseOrder(RequestOrderDetailBean rdb){
		return this.convertToBoolean(sql.update("updateRequestOrderState", rdb));
	}
	
	boolean responseOrder2(RequestOrderDetailBean rdb){		
		return this.convertToBoolean(sql.update("updateRequestOrderState2", rdb));		
	}
	
	
	List<RequestOrderBean> waitOrderlist(){
		String sp = null;
		sp = "KR001D";
		return sql.selectList("getSupplyWaitOrderList",sp);
	}
	
	List<RequestOrderBean> clearOrderlist(){
		String sp = null;
		sp = "KR001D";
		return sql.selectList("getSupplyClearOrderList",sp);
	}
	
	List<RequestOrderBean> waitOrderlistD(RequestOrderBean rb){
		List<RequestOrderBean> reList = null;
		rb.setRe_code(rb.getRe_code());
		reList = sql.selectList("getSupplyWaitOrderListD",rb);
		return reList;
	}
	
	
	List<RequestOrderBean> clearOrderlistD(RequestOrderBean rb){
		List<RequestOrderBean> reList = null;
		rb.setRe_code(rb.getRe_code());
		reList = sql.selectList("getSupplyClearOrderListD",rb);
		return reList;
	}
	
	boolean insertDL(RequestOrderDetailBean rdb) {
		return this.convertToBoolean(sql.insert("insertDL", rdb));
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

	List<ClientInfoBean> choiceCLInfo(ClientInfoBean cb) {

		List<ClientInfoBean> reList = null;
		cb.setCl_code(cb.getCl_code());
		reList = sql.selectList("choiceCLInfo", cb);

		return reList;
	}

	List<SupplyInfoBean> choiceSPInfo(SupplyInfoBean sb) {

		List<SupplyInfoBean> reList = null;
		sb.setSp_code(sb.getSp_code());
		System.out.println(sb.getSp_code());
		reList = sql.selectList("choiceSPInfo", sb);

		return reList;
	}

	List<RequestOrderBean> getTaxDill() {
		List<RequestOrderBean> reList = null;
		reList = sql.selectList("getTaxDill");
		return reList;
	}

	List<RequestOrderDetailBean> choiceDillInfo(RequestOrderDetailBean rdb) {

		List<RequestOrderDetailBean> reList = null;
		rdb.setRd_recode(rdb.getRd_recode());

		reList = sql.selectList("choiceDillInfo", rdb);

		return reList;
	}
	
}
