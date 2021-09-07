package mrone.mro.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;

@Repository
public class MroDaoCJH {
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
	
	private boolean convertToBoolean(int data) {
		return data>0? true : false;
	}
}
