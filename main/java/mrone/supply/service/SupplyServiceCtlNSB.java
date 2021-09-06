package mrone.supply.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.DeliveryBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;
import mrone.teamone.beans.SupplyInfoBean;

@Service
public class SupplyServiceCtlNSB {
	@Autowired
	SupplyDaoNSB dao;
	@Autowired
	SqlSessionTemplate sqlSession;

	
	List<RequestOrderBean> waitOrderlist() {
		
		List<RequestOrderBean> reList = null;
		reList = dao.waitOrderlist();		
		return reList;
	}
	
List<RequestOrderBean> clearOrderlist() {
		
		List<RequestOrderBean> reList = null;
		reList = dao.clearOrderlist();
		return reList;
	}
	
	List<RequestOrderBean> waitOrderlistD(RequestOrderBean rb) {
		
		List<RequestOrderBean> reList = null;
		reList = dao.waitOrderlistD(rb);
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
	
List<ClientInfoBean> getTaxCL() {	
		List<ClientInfoBean> reList = null;
		reList = dao.getTaxCL();
		return reList;
	}

List<ClientInfoBean>choiceCLInfo(ClientInfoBean cb) {	
	List<ClientInfoBean> reList = null;
	reList = dao.choiceCLInfo(cb);
	return reList;
}

List<SupplyInfoBean>choiceSPInfo(SupplyInfoBean sb) {	
	List<SupplyInfoBean> reList = null;
	reList = dao.choiceSPInfo(sb);
	return reList;
}


List<RequestOrderBean> getTaxDill() {	
	List<RequestOrderBean> reList = null;
	reList = dao.getTaxDill();
	return reList;
}
	
List<RequestOrderDetailBean>choiceDillInfo(RequestOrderDetailBean rdb) {	
	List<RequestOrderDetailBean> reList = null;
	reList = dao.choiceDillInfo(rdb);
	return reList;
}

}
		
			
	
	

