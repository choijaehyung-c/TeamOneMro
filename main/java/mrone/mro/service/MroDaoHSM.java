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
	
	ProductBean mroGetNewProductDetail(ProductBean pb){
		System.out.println(pb.getPr_code());
		return sql.selectOne("mroGetNewProductDetail", pb);
	}

	boolean mroResponseNewProduct(ProductBean pb) {
		return convertToBoolean(sql.update("mroResponseNewProduct", pb));
	}
	
	public List<ProductBean> callModifyRequestList() {
		return sql.selectList("callModifyRequestList");
	}
	
	public ProductBean mroGetModifyProductDetail(ProductBean pb) {
		return sql.selectOne("mroGetModifyProductDetail", pb);
	}

	
	public boolean mroResponseModifyProduct(ProductBean pb) {
		return convertToBoolean(sql.update("mroResponseModifyProduct", pb));
	}
	
	public boolean updatePCToPD(ProductBean pb) {
		return convertToBoolean(sql.update("updatePCToPD", pb));
	}
	
	public boolean mroResponseDeleteProduct(ProductBean pb) {
		return convertToBoolean(sql.update("mroResponseDeleteProduct", pb));
	}

	public boolean stcodeCheck(ProductBean pb) {
		return convertToBoolean(sql.selectOne("stcodeCheck", pb));
	}
	
	public boolean deleteStcode(ProductBean pb) {
		return convertToBoolean(sql.delete("deleteStcode", pb));
	}

	boolean convertToBoolean(int data) {
		return data>0? true : false;
	}


	



	
	
}
