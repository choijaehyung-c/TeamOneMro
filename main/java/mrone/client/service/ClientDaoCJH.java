package mrone.client.service;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mrone.teamone.beans.ClientOrderBean;
import mrone.teamone.beans.OrderDetailBean;

@Repository
public class ClientDaoCJH {
	@Autowired
	SqlSessionTemplate sql;
	
	
	boolean isClient(ClientOrderBean co) {
		return convertToBoolean(sql.selectOne("isClient", co));
	}
	
	boolean isClientPwd(ClientOrderBean co) {
		return convertToBoolean(sql.selectOne("isClientPwd", co));
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

	
	private boolean convertToBoolean(int data) {
		return data>0? true : false;
	}
	
}