package mrone.supply.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.SupplySearchBean;

@Repository
public class SupplyDaoCJH {
	@Autowired
	SqlSessionTemplate sql;
	
	List<RequestOrderBean> getSupplyDealList(String re_spcode){
		return sql.selectList("getSupplyDealList",re_spcode);
	}
	
	RequestOrderBean getSupplyDealDetail(String re_code) {
		return sql.selectOne("getSupplyDealDetail",re_code);
	}
	
	List<SupplySearchBean> getSearchSupplyDeal(String word){
		return sql.selectList("getSearchSupplyDeal",word);
	}
	
	
}
