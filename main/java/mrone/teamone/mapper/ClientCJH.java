package mrone.teamone.mapper;

import mrone.teamone.beans.ClientOrderBean;

public interface ClientCJH {
	void isClient(ClientOrderBean co);
	void isClientPwd(ClientOrderBean co);
	void insClientOrder(ClientOrderBean co);
}
