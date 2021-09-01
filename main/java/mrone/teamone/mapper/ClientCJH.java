package mrone.teamone.mapper;

import mrone.teamone.beans.ClientOrderBean;
import mrone.teamone.beans.OrderDetailBean;

public interface ClientCJH {
	void isClient(ClientOrderBean co);
	void isClientPwd(ClientOrderBean co);
	void insClientOrder(ClientOrderBean co);
	void insClientOrderDetail(OrderDetailBean od);
	void getOrderData(ClientOrderBean co);
}
