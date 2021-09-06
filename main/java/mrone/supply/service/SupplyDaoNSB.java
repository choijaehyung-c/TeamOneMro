package mrone.supply.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mrone.teamone.beans.DeliveryBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;



@Repository
public class SupplyDaoNSB {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	
	
	boolean responseOrder(RequestOrderDetailBean rdb){
		return this.convertToBoolean(sqlSession.update("updateRequestOrderState", rdb));
	}
	
	boolean responseOrder2(RequestOrderDetailBean rdb){		
		return this.convertToBoolean(sqlSession.update("updateRequestOrderState2", rdb));		
	}
	
	boolean delivery(RequestOrderDetailBean rdb){
		return this.convertToBoolean(sqlSession.insert("insertDL", rdb));
	}
	
	List<RequestOrderBean> clearOrderlist(){
		String sp = null;
		sp = "KR001D";
		return sqlSession.selectList("getSupplyClearOrderList",sp);
	}
	
	List<RequestOrderBean> clearOrderlistD(RequestOrderBean rb){
		List<RequestOrderBean> reList = null;
		rb.setRe_code(rb.getRe_code());
		reList = sqlSession.selectList("getSupplyClearOrderListD",rb);
		return reList;
	}
	
	boolean insertDL(RequestOrderDetailBean rdb) {
		return this.convertToBoolean(sqlSession.insert("insertDL", rdb));
	}
	
	boolean updateDL(DeliveryBean db) {
		return this.convertToBoolean(sqlSession.insert("updateDL", db));
	}
	
	
	List<DeliveryBean> getDLlist(){
		List<DeliveryBean> reList = null;
		reList = sqlSession.selectList("getDLlist");
		return reList;
	}
	
	private boolean convertToBoolean(int value) {
		return (value > 0)? true: false;
	}
}
