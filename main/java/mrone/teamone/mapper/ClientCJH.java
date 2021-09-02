package mrone.teamone.mapper;

import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.ClientOrderBean;
import mrone.teamone.beans.OrderDetailBean;

public interface ClientCJH {
	void isClient(ClientInfoBean ci);
	void isClientPwd(ClientInfoBean ci);
	void insClientOrder(ClientOrderBean co);
	void insClientOrderDetail(OrderDetailBean od);
	void getOrderData(ClientOrderBean co);
	void insRefundOrder(ClientOrderBean co);
	void insRefundOrderDetail(OrderDetailBean od);
	void getTaxBillList(ClientInfoBean ci);
	void getTaxBillDetail(ClientInfoBean ci);
}
