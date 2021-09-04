package mrone.mro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import mrone.teamone.beans.MroOrderBean;
import mrone.teamone.beans.MroOrderDetailBean;

@Service
public class MroServiceEntrance {

	@Autowired
	MroServiceCtlYJ mseIYJ;
	
		
	private ModelAndView mav =null;

	//공급사리스트
	public ModelAndView SupplyListCtl() {
		mav = new ModelAndView();	
		mav.setViewName("MroHome");
		mav.addObject("Slist",mseIYJ.SupplyList());
		
		return mav;
	}

	//고객사리스트
	public ModelAndView ClientListCtl() {
		mav = new ModelAndView();
		mav.setViewName("MroHome");
		mav.addObject("Clist",mseIYJ.ClientList());
		
		return mav;
	}

	//mro 주문대기리스트
	public List<MroOrderBean> getWaitOrderListCtl() {
		List<MroOrderBean> list =mseIYJ.getWaitOrderList(); 
		
		if(list.isEmpty()) {
			System.out.println("주문 대기 리스트가 없습니다.");
		}
		return list;
	}
	
	//주문대기,반품요청, 교환요청 상세보기
	public List<MroOrderDetailBean> getOrderDetail(String osCode) {
		
		List<MroOrderDetailBean> od = mseIYJ.getOrderDetail(osCode);

		return od;
	}

	//반품요청 리스트
	public List<MroOrderBean> getRefundListCtl() {
		List<MroOrderBean> list = mseIYJ.getRefundList();
		
		if(list.isEmpty()) {
			System.out.println("반품요청된 목록이 없습니다.");
		}
		return list;
	}
	
	
	//교환요청 리스트
	public List<MroOrderBean> getExchangeListCtl() {
		List<MroOrderBean> list = mseIYJ.getExchangeList();
		String message = "";
		
		if(list.isEmpty()) {
			message = "교환요청된 목록이 없습니다.";
		}else {
			return list;
		}
		//System.out.println(list);
		return list;
	}

}





////반품 요청 상세보기
//public List<MroOrderDetailBean> getRefundDetail(String osCode) {
//	List<MroOrderDetailBean> rd = mseIYJ.getRefundDetail(osCode);
//	System.out.println("반품디테일 :"+ rd);
//	return rd;
//}
//
////교환 요청 상세보기
//public List<MroOrderDetailBean> getExchangeDetail(String osCode) {
//	List<MroOrderDetailBean> ed = mseIYJ.getExchangeDetail(osCode);
//	System.out.println("교환디테일 :"+ ed);
//	return ed;
//}
