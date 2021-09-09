package mrone.teamone.auth;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mrone.teamone.beans.AccessBean;
import mrone.teamone.beans.AccessHistoryBean;
import mrone.teamone.beans.MroAccessBean;

@Repository
public class AuthDao {

	@Autowired
	SqlSessionTemplate sql;
	
	// 해당 아이디 단일 로그인 체크
	boolean getAccessHistorySum(AccessHistoryBean ah){
		return convertToBoolean(sql.selectOne("getAccessHistorySum",ah));
	}

	// 해당 아이디 아이피 브라우저 로그아웃전 체크
	boolean getLogOutAccessHistorySum(AccessHistoryBean ah){
		return convertToBoolean(sql.selectOne("getLogOutAccessHistorySum",ah));
	}
	
	String getLastAccessInfo(AccessHistoryBean ah) {
		return sql.selectOne("getLastAccessInfo",ah);
	}
	
	boolean isUserId(AccessBean ab) {
		System.out.println("ddd55");
		return convertToBoolean(sql.selectOne("isUserId", ab));
	}

	boolean checkPwd(AccessBean ab) {
		return convertToBoolean(sql.selectOne("checkPwd", ab));
	}
	
	String getGrade(AccessBean ab){
		return sql.selectOne("", ab);
	}
	
	boolean forceLogout(AccessHistoryBean ah) {
		return convertToBoolean(ah.getAh_table().equals("AHM")?sql.insert("forceLogoutMro", ah):sql.insert("forceLogoutSupply", ah));
	}
	
	boolean insAccessHistory(AccessHistoryBean ah) {
		return convertToBoolean(ah.getAh_table().equals("AHM")? sql.insert("insAccessHistoryMro", ah): sql.insert("insAccessHistorySupply", ah));
	}
	
	private boolean convertToBoolean(int data) {
		return data>0? true : false;
	}

	
	
	
}
