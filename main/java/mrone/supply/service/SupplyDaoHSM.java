package mrone.supply.service;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mrone.teamone.beans.MroOrderBean;
import mrone.teamone.beans.MroOrderDetailBean;
import mrone.teamone.beans.ProductBean;

@Repository
public class SupplyDaoHSM {
	
	@Autowired
	SqlSessionTemplate sql;

	public ProductBean supplyGetProductDetail(ProductBean pb) {
		
		return sql.selectOne("supplyGetProductDetail", pb);
	}

	
	public boolean supplyModifyStock(ProductBean pb) {
		
		return convertToBoolean(sql.update("supplyModifyStock", pb));
	}

	public boolean supplyRequestModify(ProductBean pb) {
		
		return convertToBoolean(sql.insert("supplyRequestModify", pb));
	}
	
	public boolean supplyRequestDelete(ProductBean pb) {
		return convertToBoolean(sql.insert("supplyRequestDelete", pb));
	}
	
	public boolean MRCheck(ProductBean pb) {
		return convertToBoolean(sql.selectOne("MRCheck", pb));
	}
	
	public boolean updateSupplyRequestModify(ProductBean pb) {
			
		return convertToBoolean(sql.update("updateSupplyRequestModify", pb));
	}

	boolean convertToBoolean(int data) {
		return data>0? true : false;
	}









	
}
