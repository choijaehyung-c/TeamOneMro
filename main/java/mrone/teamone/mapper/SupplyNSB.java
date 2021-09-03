package mrone.teamone.mapper;



import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;

public interface SupplyNSB {
	void waitOrderlist();
	void waitOrderlistD(RequestOrderBean rb);
	void responseOrder(RequestOrderDetailBean rdb);
	void responseOrder2(RequestOrderDetailBean rdb);
	
}
