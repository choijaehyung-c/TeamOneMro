package mrone.supply.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.DeliveryBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;
import mrone.teamone.beans.SupplyInfoBean;



@Repository
public class SupplyDaoNSB {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	
	
	boolean responseOrder(RequestOrderDetailBean rdb){
		return this.convertToBoolean(sqlSession.update("updateRequestOrderState", rdb));
	}
	
	boolean responseOrder2(RequestOrderDetailBean rdb){		
		return this.convertToBoolean(sqlSession.update("updateRequestOrderState2", rdb));		
	}
	
	
	List<RequestOrderBean> waitOrderlist(){
		String sp = null;
		sp = "KR001D";
		return sqlSession.selectList("getSupplyWaitOrderList",sp);
	}
	
	List<RequestOrderBean> clearOrderlist(){
		String sp = null;
		sp = "KR001D";
		return sqlSession.selectList("getSupplyClearOrderList",sp);
	}
	
	List<RequestOrderBean> waitOrderlistD(RequestOrderBean rb){
		List<RequestOrderBean> reList = null;
		rb.setRe_code(rb.getRe_code());
		reList = sqlSession.selectList("getSupplyWaitOrderListD",rb);
		return reList;
	}
	
	
	List<RequestOrderBean> clearOrderlistD(RequestOrderBean rb){
		List<RequestOrderBean> reList = null;
		rb.setRe_code(rb.getRe_code());
		reList = sqlSession.selectList("getSupplyClearOrderListD",rb);
		return reList;
	}
	
	boolean insertDL(RequestOrderDetailBean rdb) {
		return this.convertToBoolean(sqlSession.insert("insertDL", rdb));
	}
	
	boolean updateDL(DeliveryBean db) {
		return this.convertToBoolean(sqlSession.insert("updateDL", db));
	}
	
	
	List<DeliveryBean> getDLlist(){
		List<DeliveryBean> reList = null;
		reList = sqlSession.selectList("getDLlist");
		return reList;
	}
	
	List<ClientInfoBean> getTaxCL(){
		List<ClientInfoBean> reList = null;
		reList = sqlSession.selectList("getTaxCL");
		return reList;
	}
	
	List<ClientInfoBean> choiceCLInfo(ClientInfoBean cb){
		
		List<ClientInfoBean> reList = null;
		cb.setCl_code(cb.getCl_code());
		reList = sqlSession.selectList("choiceCLInfo", cb);
		
		return reList;
	}
	
	List<SupplyInfoBean> choiceSPInfo(SupplyInfoBean sb){
		
		List<SupplyInfoBean> reList = null;
		sb.setSp_code(sb.getSp_code());
		System.out.println(sb.getSp_code());
		reList = sqlSession.selectList("choiceSPInfo", sb);
		
		return reList;
	}
	
	List<RequestOrderBean> getTaxDill(){
		List<RequestOrderBean> reList = null;
		reList = sqlSession.selectList("getTaxDill");
		return reList;
	}
	
	
List<RequestOrderDetailBean> choiceDillInfo(RequestOrderDetailBean rdb){
		
		List<RequestOrderDetailBean> reList = null;
		rdb.setRd_recode(rdb.getRd_recode());
		
		reList = sqlSession.selectList("choiceDillInfo", rdb);
		
		return reList;
	}

	private boolean convertToBoolean(int value) {
		return (value > 0)? true: false;
	}
}
