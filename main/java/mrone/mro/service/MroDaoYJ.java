package mrone.mro.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	
	boolean convertToBoolean(int data) {
		return data>0? true : false;
	}
}
