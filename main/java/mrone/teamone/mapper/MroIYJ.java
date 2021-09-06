package mrone.teamone.mapper;

import java.util.List;

import mrone.teamone.beans.DeliveryBean;
import mrone.teamone.beans.MroOrderBean;
import mrone.teamone.beans.MroOrderDetailBean;
import mrone.teamone.beans.RequestOrderBean;

public interface MroIYJ {
	void getSupplyList();
	void getClientList();
	void getWaitOrderList();
	void getOrderDetail(String osCode); //주문대기, 반품요청, 교환요청 상세보기
	void getRefundList();
	void getExchangeList();
	//void getRefundDetail(String osCode);
	//void getExchangeDetail(String osCode);
	void getRefundListSp(RequestOrderBean re);
	void supplyReceiveAsDetail(RequestOrderBean re);
	void supplyResponseRefund(RequestOrderBean re); //od 업데이트
	void supplyResponseRefundOS(RequestOrderBean re); // os 업데이트
	void supplyOSInfo(RequestOrderBean re);
	void supplyOCInfo(String osCode);
	void supplyPDInfo(String osCode);
	void getCount();
	void insNewOrders(RequestOrderBean re);
	void insNewOrderDetail(List<MroOrderDetailBean> mod);
	void supplySearchAs(RequestOrderBean re);
	void supplyResponseExchangeOD(RequestOrderBean re);
	void supplyResponseExchangeOS(RequestOrderBean re);
	void supplyAskDelivery(DeliveryBean db);
	void makeDeliveryLocate();
	void maxLCcode();
}
