package mrone.mro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import mrone.teamone.beans.MroOrderBean;
import mrone.teamone.beans.OrderDetailBean;

@Service
public class MroServiceEntrance {

	@Autowired
	MroServiceCtlYJ mseIYJ;
	
		
	private ModelAndView mav =null;

	public ModelAndView SupplyListCtl() {
		mav = new ModelAndView();	
		mav.setViewName("MroHome");
		mav.addObject("Slist",mseIYJ.SupplyList());
		
		return mav;
	}

	public ModelAndView ClientListCtl() {
		mav = new ModelAndView();
		mav.setViewName("MroHome");
		mav.addObject("Clist",mseIYJ.ClientList());
		
		return mav;
	}

	//mro 주문대기리스트
	public List<MroOrderBean> getWaitOrderListCtl() {
		List<MroOrderBean> list =mseIYJ.getWaitOrderList(); 
		
		//mav.setViewName("MroHome");
		System.out.println(list);
		
		return list;
	}
	
	public ModelAndView getOrderDetail(OrderDetailBean od) {
		//System.out.println(od.getOD_OSCODE());
		System.out.println(od.getOD_OSCODE());
		return null;
	}


	
	

}
