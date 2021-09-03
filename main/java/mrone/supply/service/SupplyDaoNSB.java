package mrone.supply.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	
	
	
	private boolean convertToBoolean(int value) {
		return (value > 0)? true: false;
	}
}
