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
		System.out.println(result);
		return result;
	}

	boolean convertToBoolean(int data) {
		return data>0?true:false;
	}

}
