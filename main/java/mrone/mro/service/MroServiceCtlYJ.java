package mrone.mro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.MroOrderBean;
import mrone.teamone.beans.MroOrderDetailBean;
import mrone.teamone.beans.SupplyInfoBean;

@Service
public class MroServiceCtlYJ {

	@Autowired
	MroDaoYJ dao;

	private ModelAndView mav = null;

	List<SupplyInfoBean> SupplyList() {
		List<SupplyInfoBean> list;
		list = dao.getSupplyList();
		

		return list;
	}

	List<ClientInfoBean> ClientList() {
		List<ClientInfoBean> list;
		list = dao.getClientList();

		return list;
	}

	List<MroOrderBean> getWaitOrderList(){
		List<MroOrderBean> waitOrderList;
		waitOrderList = dao.getWaitOrderList();

		for(int i=0; i<waitOrderList.size(); i++) {
			if(waitOrderList.get(i).getOs_state().equals("OR")) {
				waitOrderList.get(i).setOs_state("대기");
			}
		}
		
		//System.out.println(waitOrderList);
		return waitOrderList;
	}
	
	List<MroOrderDetailBean> getOrderDetail(String osCode) {
		List<MroOrderDetailBean> od;
		od = dao.getOrderDetail(osCode);
		System.out.println(od);
		
		return od;
	}

	List<MroOrderBean> getRefundList() {
		List<MroOrderBean> list = dao.getRefundList();
		
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getOs_state().equals("RR")) {
				list.get(i).setOs_state("반품요청");
			}
		}
		return list;
	}
	

	 List<MroOrderBean> getExchangeList() {
		 List<MroOrderBean> list = dao.getExchangeList();
		 
			for(int i=0; i<list.size(); i++) {
				if(list.get(i).getOs_state().equals("ER")) {
					list.get(i).setOs_state("교환요청");
				}
			}
			return list;
	}


}

//
//List<MroOrderDetailBean> getRefundDetail(String osCode) {
//	List<MroOrderDetailBean> rd;
//	rd = dao.getRefundDetail(osCode);
//	return rd;
//}
//
//List<MroOrderDetailBean> getExchangeDetail(String osCode) {
//	List<MroOrderDetailBean> ed;
//	ed = dao.getExchangeDetail(osCode);
//	return ed;
//}
