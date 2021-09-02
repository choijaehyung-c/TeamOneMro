package mrone.supply.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import mrone.teamone.beans.RequestOrderBean;

@Service
public class SupplyServiceCtlNSB {
	@Autowired
	SupplyDaoNSB dao;
	@Autowired
	SqlSessionTemplate sqlSession;

	
	List<RequestOrderBean> waitOrderlist() {
		
		List<RequestOrderBean> reList = null;
		try {
			String sp = null;
			sp = "KR001D";
			
			reList = sqlSession.selectList("getSupplyWaitOrderList",sp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reList;
	}
	
	List<RequestOrderBean> waitOrderlistD(RequestOrderBean rb) {
		
		List<RequestOrderBean> reList = null;
		try {
			rb.setRe_code(rb.getRe_code());
			System.out.println(rb.getRe_code());
			reList = sqlSession.selectList("getSupplyWaitOrderListD",rb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reList;
	}
	
}
