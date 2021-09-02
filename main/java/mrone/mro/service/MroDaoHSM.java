package mrone.mro.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class MroDaoHSM {
	
	@Autowired
	SqlSessionTemplate sql;

	

	
	
	boolean convertToBoolean(int data) {
			return data>0? true : false;
	}
	
	
}
