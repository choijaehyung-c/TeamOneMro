package mrone.mro.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.MroOrderBean;
import mrone.teamone.beans.MroOrderDetailBean;
import mrone.teamone.beans.SupplyInfoBean;

@Repository
public class MroDaoYJ {
	
	@Autowired
	SqlSessionTemplate sql;

	//공급사리스트
	List<SupplyInfoBean> getSupplyList(){
		List<SupplyInfoBean> list;		
		list =sql.selectList("getSupplyList");

		return list;
	}
	
	//고객사 리스트
	List<ClientInfoBean> getClientList(){
		List<ClientInfoBean> list;
		list = sql.selectList("getClientList");
		
		return list;
	}

	//주문대기리스트
	 List<MroOrderBean> getWaitOrderList() {
		List<MroOrderBean> list;
		list = sql.selectList("getWaitOrderList");
		
		return list;
	}
	 
	 //주문대기 디테일
	 List<MroOrderDetailBean> getOrderDetail(String osCode) {
		
		 return sql.selectList("getOrderDetail", osCode);
	 }
	 
		
	
	 //반품요청 리스트 
	 List<MroOrderBean> getRefundList() {
		
		return sql.selectList("getRefundList");
	}
	 
	 //교환요청 리스트
	public List<MroOrderBean> getExchangeList() {
		return sql.selectList("getExchangeList");
	}	 
	 
	 
	 boolean convertToBoolean(int data) {
			return data>0? true : false;
	}


}
