package mrone.supply.service;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mrone.teamone.beans.DeliveryBean;
import mrone.teamone.beans.MroOrderBean;
import mrone.teamone.beans.MroOrderDetailBean;
import mrone.teamone.beans.ProductBean;
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

	boolean supplyResponseRefundRD(RequestOrderBean re) {

		return this.convertToBoolean(sql.update("supplyResponseRefundRD",re));
	}

	boolean supplyResponseRefundRE(RequestOrderBean re) {

		return this.convertToBoolean(sql.update("supplyResponseRefundRE",re));
	}

	boolean supplyResponseExchangeOD(RequestOrderBean re) {
		return this.convertToBoolean(sql.update("supplyResponseExchangeOD",re));
	}

	boolean supplyResponseExchangeOS(RequestOrderBean re) {
		return this.convertToBoolean(sql.update("supplyResponseExchangeOS",re));
	}
	
	boolean supplyResponseExchageRD(RequestOrderBean re) {
		return this.convertToBoolean(sql.update("supplyResponseExchageRD", re));
	}
	
	boolean supplyResponseExchageRE(RequestOrderBean re) {
		return this.convertToBoolean(sql.update("supplyResponseExchageRE",re));
	}
	
	List<MroOrderDetailBean> selRequest(RequestOrderBean re) {
		return sql.selectList("selRequest",re);

	}

	boolean insNewOrders(RequestOrderBean re) {
		return this.convertToBoolean(sql.insert("insNewOrders", re));
	}

	boolean insNewOd(List<MroOrderDetailBean> mod) {
		int list=0;
		
		for(int i=0; i<mod.size(); i++) {
			list = sql.insert("insNewOd", mod.get(i));
		}		
		return this.convertToBoolean(list);
	}
	
	boolean insNewRequest(RequestOrderBean re) {
		return this.convertToBoolean(sql.insert("insNewRequest",re));
	}

	boolean insNewRd(List<MroOrderDetailBean> mod) {
	int list=0;
		
		for(int i=0; i<mod.size(); i++) {
			list = sql.insert("insNewRd", mod.get(i));
		}		
		return this.convertToBoolean(list);
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


	List<RequestOrderBean> supplySearchAs(RequestOrderBean re){

		return sql.selectList("supplySearchAs", re);
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

	//공급사 카테고리 받아오는 메서드
	public List<ProductBean> supplyGetCategory() {
		
		
		return sql.selectList("supplyGetCategory");
	}

	public List<ProductBean> supplyProductList(ProductBean pd) {
		
		return sql.selectList("supplyProductList",pd);
	}

	public List<ProductBean> supplySearchProduct(ProductBean pd) {
		
		return sql.selectList("supplySearchProduct", pd);
	}











}
