package mrone.mro.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.MroOrderBean;
import mrone.teamone.beans.MroOrderDetailBean;
import mrone.teamone.beans.OrderDetailBean;
import mrone.teamone.beans.ProductBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;
import mrone.teamone.beans.SupplyInfoBean;

@Repository
public class MroDao {
	@Autowired
	SqlSessionTemplate sql;
	
	boolean insMroRequestOrder(RequestOrderBean ro) {
		return convertToBoolean(sql.insert("insMroRequestOrder", ro));
	}
	
	boolean insMroRequestOrderDetail(RequestOrderDetailBean rd) {
		return convertToBoolean(sql.insert("insMroRequestOrderDetail", rd));
	}
	
	String getRequestOrderData(RequestOrderBean ro){
		return sql.selectOne("getRequestOrderData",ro);
	}
	
	List<ProductBean> getRequestRegisterNewProductList(){
		List<ProductBean> PBList;		
		PBList =sql.selectList("getRequestRegisterNewProductList");

		return PBList;
	}
	
	ProductBean mroGetNewProductDetail(ProductBean pb){
		System.out.println(pb.getPr_code());
		return sql.selectOne("mroGetNewProductDetail", pb);
	}

	boolean mroResponseNewProduct(ProductBean pb) {
		return convertToBoolean(sql.update("mroResponseNewProduct", pb));
	}
	
	List<ProductBean> callModifyRequestList() {
		return sql.selectList("callModifyRequestList");
	}
	
	ProductBean mroGetModifyProductDetail(ProductBean pb) {
		return sql.selectOne("mroGetModifyProductDetail", pb);
	}

	
	boolean mroResponseModifyProduct(ProductBean pb) {
		return convertToBoolean(sql.update("mroResponseModifyProduct", pb));
	}
	
	boolean updatePCToPD(ProductBean pb) {
		return convertToBoolean(sql.update("updatePCToPD", pb));
	}
	
	boolean mroResponseDeleteProduct(ProductBean pb) {
		return convertToBoolean(sql.update("mroResponseDeleteProduct", pb));
	}

	boolean stcodeCheck(ProductBean pb) {
		return convertToBoolean(sql.selectOne("stcodeCheck", pb));
	}
	
	boolean deleteStcode(ProductBean pb) {
		return convertToBoolean(sql.delete("deleteStcode", pb));
	}

	// 공급사리스트
	List<SupplyInfoBean> getSupplyList() {
		List<SupplyInfoBean> list;
		list = sql.selectList("getSupplyList");

		return list;
	}

	// 고객사 리스트
	List<ClientInfoBean> getClientList() {
		List<ClientInfoBean> list;
		list = sql.selectList("getClientList");

		return list;
	}

	// 주문대기리스트
	List<MroOrderBean> getWaitOrderList() {
		List<MroOrderBean> list;
		list = sql.selectList("getWaitOrderList");

		return list;
	}

	// 주문대기,반품요청, 교환요청 디테일
	List<MroOrderDetailBean> getOrderDetail(String osCode) {

		return sql.selectList("getOrderDetail", osCode);
	}
	
	List<MroOrderBean> getCompleteOrderList(){
		return sql.selectList("getCompleteOrderList");
	
}

	// 반품요청 리스트
	List<MroOrderBean> getRefundList() {

		return sql.selectList("getRefundList");
	}

	// 교환요청 리스트
	List<MroOrderBean> getExchangeList() {
		return sql.selectList("getExchangeList");
	}


	//new
	List<SupplyInfoBean> mroSearchSupplyList(String word) {
	
		return sql.selectList("getSearchSupplyList",word);
	}
	
	//new
	List<ClientInfoBean> mroSearchClientList(String word) {
		return sql.selectList("getSearchClientList",word);
	}

	//new
	boolean mroDelClient(String code) {
		
		return this.convertToBoolean(sql.delete("mroDelClient",code));
	}
	
	//new
	 boolean mroDelSupply(String code) {
		// TODO Auto-generated method stub
		return this.convertToBoolean(sql.delete("mroDelSupply",code));
	}
	 
	List<OrderDetailBean> getRanking() {
			
		return sql.selectList("getRanking");
	}
	
	boolean convertToBoolean(int data) {
		return data > 0 ? true : false;
	}
	
	
}
