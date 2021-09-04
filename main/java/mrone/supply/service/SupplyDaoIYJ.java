package mrone.supply.service;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mrone.teamone.beans.MroOrderBean;
import mrone.teamone.beans.MroOrderDetailBean;

@Repository
public class SupplyDaoIYJ {
	
	@Autowired
	SqlSessionTemplate sql;

	List<MroOrderBean> getRefundListSp(MroOrderDetailBean mod) {
		List<MroOrderBean> list = sql.selectList("getRefundListSp",mod);
		//System.out.println(list);
		return list;
	}

	 List<MroOrderDetailBean> supplyReceiveAsDetail(MroOrderBean mo) {
		List<MroOrderDetailBean> list = sql.selectList("supplyReceiveAsDetail",mo);
		return list;
	}

	 boolean supplyResponseRefund(MroOrderBean mo) {
		
		return this.convertToBoolean(sql.update("supplyResponseRefund", mo));
	}
	 
	 List<MroOrderDetailBean> supplyOCInfo(MroOrderBean mo) {
		 return sql.selectList("supplyOCInfo",mo);
	 }
	 
	 boolean convertToBoolean(int data) {
		 return data>0?true:false;
	 }

}
