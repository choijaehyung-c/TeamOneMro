package mrone.teamone.mapper;

import mrone.teamone.beans.AccessBean;
import mrone.teamone.beans.MroAccessBean;

public interface Auth {
	void getAccessHistorySum(MroAccessBean ma);
	void getLogOutAccessHistorySum(MroAccessBean ma);
	void getLastAccessInfo(MroAccessBean ma);
	void forceLogout(MroAccessBean ma);
	void isUserId(MroAccessBean ma);
	void checkPwd(AccessBean ab);
	void insAccessHistory(MroAccessBean ma);
}
