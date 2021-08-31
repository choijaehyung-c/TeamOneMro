package mrone.teamone.auth;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mrone.teamone.beans.AccessBean;
import mrone.teamone.beans.MroAccessBean;

@Repository
public class AuthDao {

	@Autowired
	SqlSessionTemplate sql;
	
	// 해당 아이디 단일 로그인 체크
	boolean getAccessHistorySum(MroAccessBean ma){
		return convertToBoolean(sql.selectOne("getAccessHistorySum",ma));
	}

	// 해당 아이디 아이피 브라우저 로그아웃전 체크
	boolean getLogOutAccessHistorySum(MroAccessBean ma){
		return convertToBoolean(sql.selectOne("getLogOutAccessHistorySum",ma));
	}
	
	String getLastAccessInfo(MroAccessBean ma) {
		return sql.selectOne("getLastAccessInfo",ma);
	}
	
	boolean forceLogout(MroAccessBean ma) {
		return convertToBoolean(sql.insert("forceLogout", ma));
	}
	
	boolean isUserId(AccessBean ab) {
		return convertToBoolean(sql.selectOne("isUserId", ab));
	}

	boolean checkPwd(AccessBean ab) {
		return convertToBoolean(sql.selectOne("isUserId", ab));
	}
	
	String getGrade(AccessBean ab){
		return sql.selectOne("", ab);
	}
	boolean insAccessHistory(MroAccessBean ma) {
		System.out.println(ma.getAHM_BROWSER()+ma.getAHM_CODE()+ma.getAHM_METHOD()+ma.getAHM_PRIVATEIP()+ma.getAHM_PUBLICIP()+ma.getMD_PWD());
		return convertToBoolean(sql.insert("insAccessHistory", ma));
	}
	
	private boolean convertToBoolean(int data) {
		return data>0? true : false;
	}

	
	
	
}
