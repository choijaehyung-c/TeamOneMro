package mrone.teamone.mapper;

import java.util.List;

import mrone.teamone.beans.MroOrderBean;
import mrone.teamone.beans.MroOrderDetailBean;

public interface MroIYJ {
	void getSupplyList();
	void getClientList();
	void getWaitOrderList();
	void getOrderDetail(String osCode); //주문대기, 반품요청, 교환요청 상세보기
	void getRefundList();
	void getExchangeList();
	//void getRefundDetail(String osCode);
	//void getExchangeDetail(String osCode);
	void getRefundListSp(MroOrderDetailBean mod);
	void supplyReceiveAsDetail(MroOrderBean mo);
	void supplyResponseRefund(MroOrderBean mo); //od 업데이트
	void supplyResponseRefundOS(MroOrderBean mo); // os 업데이트
	void supplyOSInfo(MroOrderBean mo);
	void supplyOCInfo(String osCode);
	void getCount();
	void insNewOrders(MroOrderBean mo);
	void insNewOrderDetail(List<MroOrderDetailBean> mod);
	void supplySearchAs(MroOrderBean mo);
}
