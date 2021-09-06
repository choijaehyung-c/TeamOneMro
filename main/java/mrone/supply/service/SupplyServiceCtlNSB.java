package mrone.supply.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrone.teamone.beans.DeliveryBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;

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
	
List<RequestOrderBean> clearOrderlist() {
		
		List<RequestOrderBean> reList = null;
		reList = dao.clearOrderlist();

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
	
	List<RequestOrderBean> clearOrderlistD(RequestOrderBean rb) {
		
		List<RequestOrderBean> reList = null;
		
		reList = dao.clearOrderlistD(rb);
		
		return reList;
	}
	
	String responseOrder(RequestOrderDetailBean rdb) {		
	String message= null;
		rdb.setRd_recode(rdb.getRd_recode());
		for(int i=0; i<rdb.getRd_recode().length(); i++) {
			if(dao.responseOrder(rdb)) {
				if(dao.responseOrder2(rdb)) {
					message = "접수완료";		
				}
		 }				 
		}
		dao.insertDL(rdb);
		return message;
	}
	
	List<DeliveryBean> getDLlist() {	
		List<DeliveryBean> reList = null;	
			reList = dao.getDLlist();	
		return reList;
	}
	

	String updateDL(DeliveryBean db) {		
	String message= null;
		db.setDl_code(db.getDl_code());
		db.setDl_dvcode(db.getDl_dvcode());	
			if(dao.updateDL(db)) {				
					message = "배정완료";					
		 }				 
	
		return message;
	}
	
	
}
		
			
	
	

