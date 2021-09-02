package mrone.teamone.mapper;

public interface MroIYJ {
	void getSupplyList();
	void getClientList();
	void getWaitOrderList();
	void getOrderDetail(String osCode); //주문대기, 반품요청, 교환요청 상세보기
	void getRefundList();
	void getExchangeList();
	//void getRefundDetail(String osCode);
	//void getExchangeDetail(String osCode);
}
