package mrone.teamone.mapper;

import java.util.List;

import mrone.teamone.beans.RequestOrderBean;

public interface SupplyNSB {
	void waitOrderlist();
	void waitOrderlistD(List<RequestOrderBean> rc);
	
}
