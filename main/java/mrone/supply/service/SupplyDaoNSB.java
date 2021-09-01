package mrone.supply.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mrone.teamone.beans.RequestOrderBean;



@Repository
public class SupplyDaoNSB {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	
	
	List<RequestOrderBean> getWaitOrderList(RequestOrderBean rb){
		return sqlSession.selectList("getSupplyWaitOrderList", rb);
	}
}
