package mrone.supply.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrone.teamone.beans.RequestOrderBean;

@Service
public class SupplyServiceCtlNSB {
	
	@Autowired
	SqlSessionTemplate sqlSession;

	
	List<RequestOrderBean> waitOrderlist() {	
			List<RequestOrderBean> reList = null;
			
			try {
				String rb = null;
				rb = ("1235678901");
				reList = sqlSession.selectList("getWaitOrderList", rb);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return reList;
			
	}
	
}
