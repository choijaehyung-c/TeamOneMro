package mrone.teamone.mapper;

public interface Auth {
	void getAccessHistorySum();
	void getLogOutAccessHistorySum();
	void getLastAccessInfo();
	void forceLogout();
	void isUserId();
	void checkPwd();
	void insAccessHistory();
}
