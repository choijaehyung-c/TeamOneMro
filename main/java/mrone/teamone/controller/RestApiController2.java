package mrone.teamone.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrone.mro.service.MroServiceEntrance;
import mrone.teamone.beans.MroOrderDetailBean;
import mrone.teamone.beans.MroOrderBean;

@RestController
@RequestMapping("/vue2")
public class RestApiController2 {
	
	@Autowired
	MroServiceEntrance mse;
	
	//주문대기 리스트 받아오기
	@PostMapping("/mroOrderListForm")
	public List<MroOrderBean> mroOrderListForm(){
		//System.out.println("Restcontroller진입");
		
		return mse.getWaitOrderListCtl();
		
	}
	
	//주문대기 상세보기
	@PostMapping("/mroGetOrderDetail")
	public List<MroOrderDetailBean> mroGetOrderDetail(@RequestBody String osCode){
		System.out.println(osCode);
		
		return mse.getOrderDetail(osCode);
		
	}
	
	//반품요청 리스트 받아오기
	@PostMapping("/mroRefundListForm")
	public List<MroOrderBean> mroRefundListForm(){
		//System.out.println("반품요청");
		
		return mse.getRefundListCtl();
		
		
	}
	
	//교환요청 리스트 받아오기
	@PostMapping("/mroExchangeListForm")
	public List<MroOrderBean> mroExchangeListForm(){
		//System.out.println("교환요청");
		
		return mse.getExchangeListCtl();
		
	}
	
	
	
}
