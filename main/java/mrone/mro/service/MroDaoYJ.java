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

	
	List<SupplyInfoBean> getSupplyList(){
		List<SupplyInfoBean> list;		
		list =sql.selectList("getSupplyList");

		return list;
	}
	
	List<ClientInfoBean> getClientList(){
		List<ClientInfoBean> list;
		list = sql.selectList("getClientList");
		
		return list;
	}


	 List<MroOrderBean> getWaitOrderList() {
		List<MroOrderBean> list;
		list = sql.selectList("getWaitOrderList");
		
		return list;
	}
	 
	 List<MroOrderDetailBean> getOrderDetail(String osCode) {
		
		 return sql.selectList("getOrderDetail", osCode);
	 }
	 
		
	boolean convertToBoolean(int data) {
			return data>0? true : false;
	}
}
