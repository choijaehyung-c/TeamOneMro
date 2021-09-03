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

	List<MroOrderDetailBean> getRefundListSp(MroOrderDetailBean mod) {
		List<MroOrderDetailBean> list = sql.selectList("getRefundListSp",mod);
		
		return list;
	}

	 List<MroOrderDetailBean> supplyReceiveAsDetail(MroOrderBean mo) {
		List<MroOrderDetailBean> list = sql.selectList("supplyReceiveAsDetail",mo);
		return null;
	}

}
