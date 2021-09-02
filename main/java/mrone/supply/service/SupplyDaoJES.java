package mrone.supply.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mrone.teamone.beans.ProductBean;
import mrone.teamone.beans.SupplyInfoBean;

@Repository
public class SupplyDaoJES {
	@Autowired
	SqlSessionTemplate sqlSession;

	List<ProductBean> insProdcutList() {
		List<ProductBean> prlist;
		prlist = sqlSession.selectList("insProdcutList");
		return prlist;	
	}

	List<ProductBean> insNewProdcutList() {
		List<ProductBean> nprlist;
		nprlist = sqlSession.selectList("insNewProdcutList");
		return nprlist;	
	}
	List<ProductBean> insProdcutListDitail(String prcode) {
		return sqlSession.selectList("insProdcutListDitail", prcode);	
	}

	boolean convertToBoolean(int data) {
		return data>0? true : false;
	}

}
