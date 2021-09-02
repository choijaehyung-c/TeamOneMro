package mrone.mro.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mrone.teamone.beans.ProductBean;
import mrone.teamone.beans.SupplyInfoBean;



@Repository
public class MroDaoHSM {
	
	@Autowired
	SqlSessionTemplate sql;

	List<ProductBean> getRequestRegisterNewProductList(){
		List<ProductBean> PBList;		
		PBList =sql.selectList("getRequestRegisterNewProductList");

		return PBList;
	}

	
	
	boolean convertToBoolean(int data) {
			return data>0? true : false;
	}
	
	
}
