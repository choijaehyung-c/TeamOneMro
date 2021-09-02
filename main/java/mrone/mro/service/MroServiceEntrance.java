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
		
		return list;
	}
	
	public MroOrderDetailBean getOrderDetail(String osCode) {
		
		MroOrderDetailBean od = mseIYJ.getOrderDetail(osCode);

		return od;
	}


	
	

}
