package mrone.supply.service;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mrone.teamone.beans.DeliveryBean;
import mrone.teamone.beans.MroOrderBean;
import mrone.teamone.beans.MroOrderDetailBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;

@Repository
public class SupplyDaoIYJ {

	@Autowired
	SqlSessionTemplate sql;

	List<RequestOrderBean> getRefundListSp(RequestOrderBean re) {
		List<RequestOrderBean> list = sql.selectList("getRefundListSp",re);
		//System.out.println(list);
		return list;
	}

	List<RequestOrderDetailBean> supplyReceiveAsDetail(RequestOrderBean ro) {
		List<RequestOrderDetailBean> list = sql.selectList("supplyReceiveAsDetail",ro);
		return list;
	}

	boolean supplyResponseRefund(RequestOrderBean re) {
		return this.convertToBoolean(sql.update("supplyResponseRefund", re));
	}
	
	boolean supplyResponseRefundOS(RequestOrderBean re) {
		return this.convertToBoolean(sql.update("supplyResponseRefundOS",re));
	}
	
	boolean supplyResponseExchangeOD(MroOrderBean mo) {
		return this.convertToBoolean(sql.update("supplyResponseExchangeOD",mo));
	}
	
	boolean supplyResponseExchangeOS(MroOrderBean mo) {
		return this.convertToBoolean(sql.update("supplyResponseExchangeOS",mo));
	}

	//주문코드의 os정보
	 List<MroOrderBean> supplyOSInfo(RequestOrderBean re) {
		
		return sql.selectList("supplyOSInfo", re);
	}
	
	 //주문코드의 od정보
	List<MroOrderDetailBean> supplyOCInfo(String osCode) {
		return sql.selectList("supplyOCInfo",osCode);
	}
	
	List<MroOrderDetailBean> supplyPDInfo(String osCode) {
		return sql.selectList("supplyPDInfo",osCode);
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


	
	boolean insNewOrders(RequestOrderBean re) {
		System.out.println("dao : "+re);
		return this.convertToBoolean(sql.insert("insNewOrders",re));
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
	
	//교환 배달요청
	 boolean supplyAskDelivery(DeliveryBean db) {
		System.out.println("배달요청 : " + db);
		return this.convertToBoolean(sql.insert("supplyAskDelivery", db));
	}
	
	//배달 locate만들기
	boolean makeDeliveryLocate() {
		return this.convertToBoolean(sql.insert("makeDeliveryLocate"));
	}
	
	String maxLCcode() {				
		return sql.selectOne("maxLCcode");
	}
	
	boolean convertToBoolean(int data) {
		return data>0?true:false;
	}






}
