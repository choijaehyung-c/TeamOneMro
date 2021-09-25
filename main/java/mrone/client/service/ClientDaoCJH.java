package mrone.client.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.ClientOrderBean;
import mrone.teamone.beans.OrderDetailBean;
import mrone.teamone.beans.TaxBean;

@Repository
class ClientDaoCJH {
	@Autowired
	SqlSessionTemplate sql;
	
	
	boolean isClient(ClientInfoBean ci) {
		return convertToBoolean(sql.selectOne("isClient", ci));
	}
	
	boolean isClientPwd(ClientInfoBean ci) {
		return convertToBoolean(sql.selectOne("isClientPwd", ci));
	}
	
	String getOrderData(ClientOrderBean co){
		return sql.selectOne("getOrderData",co);
	}
	
	boolean insClientOrder(ClientOrderBean co) {
		return convertToBoolean(sql.insert("insClientOrder", co));
	}
	
	boolean insClientOrderDetail(OrderDetailBean od) {
		return convertToBoolean(sql.insert("insClientOrderDetail", od));
	}
	
	List<TaxBean> getTaxBillList(ClientInfoBean ci){
		return sql.selectList("getTaxBillList", ci);
	}
	
	TaxBean getTaxBillDetail(ClientInfoBean ci){
		return sql.selectOne("getTaxBillDetail",ci);
	}
	
	private boolean convertToBoolean(int data) {
		return data>0? true : false;
	}
	
	boolean updOrderDecide(String os_code) {
		return this.convertToBoolean(sql.update("updOrderDecide",os_code));
	}
	
	boolean updOrderDetailDecide(String os_code) {
		return this.convertToBoolean(sql.update("updOrderDetailDecide",os_code));
	}
	
	boolean updRequestDecide(String os_code) {
		return this.convertToBoolean(sql.update("updRequestDecide",os_code));
	}
	
	boolean updRequestDetailDecide(String re_code) {
		return this.convertToBoolean(sql.update("updRequestDetailDecide",re_code));
	}
	
	String getOscodeRe(String os_code) {
		return sql.selectOne("getOscodeRe",os_code);
	}
}