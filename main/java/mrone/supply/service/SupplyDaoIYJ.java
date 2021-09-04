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
	
	boolean supplyResponseRefundOS(MroOrderBean mo) {
		return this.convertToBoolean(sql.update("supplyResponseRefundOS",mo));
	}

	//주문코드의 os정보
	 List<MroOrderBean> supplyOSInfo(MroOrderBean mo) {
		
		return sql.selectList("supplyOSInfo", mo);
	}
	
	 //주문코드의 od정보
	List<MroOrderDetailBean> supplyOCInfo(String osCode) {
		return sql.selectList("supplyOCInfo",osCode);
	}
	
	//오늘날짜 몇번까지 만들어졌는지 확인 
	String checkCount() {

		int number;

		if(sql.selectOne("getCount")!=null) {
			number = sql.selectOne("getCount");
		}else {
			number = 0;
		}
		String result = (number) + ""; 

		for(int add = result.length(); add<5; add++) {
			result = "0" + result;
		}
	
		return result;
	}

	//오늘날짜 몇번까지 만들어졌는지 확인 + 숫자 증가
	String getCount() {

		int number;

		if(sql.selectOne("getCount")!=null) {
			number = sql.selectOne("getCount");
		}else {
			number = 0;
		}
		String result = (number+1) + ""; 

		for(int add = result.length(); add<5; add++) {
			result = "0" + result;
		}
	
		return result;
	}
	
	boolean insNewOrders(MroOrderBean mo) {
		System.out.println("dao : "+mo);
		return this.convertToBoolean(sql.insert("insNewOrders",mo));
	}

	
	boolean insNewOrderDetail(List<MroOrderDetailBean> mod) {
		int list=0;
		System.out.println(mod);
		for(int i=0; i<mod.size(); i++) {
			list = sql.insert("insNewOrderDetail", mod.get(i));
		}		
		return this.convertToBoolean(list);
	}
	
	List<MroOrderBean> supplySearchAs(MroOrderBean mo){
		
		return sql.selectList("supplySearchAs", mo);
	}
	
	
	
	boolean convertToBoolean(int data) {
		return data>0?true:false;
	}




}
