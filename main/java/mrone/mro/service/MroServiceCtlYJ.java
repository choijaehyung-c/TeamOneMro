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
			if(waitOrderList.get(i).getOS_STATE().equals("W")) {
				waitOrderList.get(i).setOS_STATE("대기");
			}else if(waitOrderList.get(i).getOS_STATE().equals("B")) {
				waitOrderList.get(i).setOS_STATE("상품준비중");
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



}
