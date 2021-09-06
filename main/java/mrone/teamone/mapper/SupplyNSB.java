package mrone.teamone.mapper;



import java.util.List;

import mrone.teamone.beans.DeliveryBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;

public interface SupplyNSB {
	void waitOrderlist();
	void clearOrderlist();
	void waitOrderlistD(RequestOrderBean rb);
	void clearOrderlistD(RequestOrderBean rb);
	void responseOrder(RequestOrderDetailBean rdb);
	void responseOrder2(RequestOrderDetailBean rdb);
	void insertDL(RequestOrderDetailBean rdb);
	void getDLlist();
	void updateDL(DeliveryBean db);
	
}
