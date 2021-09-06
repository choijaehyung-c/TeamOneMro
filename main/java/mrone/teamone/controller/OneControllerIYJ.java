package mrone.teamone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import mrone.mro.service.MroServiceEntrance;
import mrone.supply.service.SupplyServiceIYJ;
import mrone.teamone.beans.MroOrderBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;
import mrone.teamone.utill.Encryption;
import mrone.teamone.utill.ProjectUtils;

@Controller
@RequestMapping("/iyj")
public class OneControllerIYJ {
	
	ModelAndView mav;
	@Autowired
	MroServiceEntrance mse;
	
	@Autowired
	SupplyServiceIYJ ssin;
	
	@Autowired
	private ProjectUtils pu;
	@Autowired
	Encryption enc;
	
	
	@PostMapping("/mroSupplyListForm")
	public ModelAndView mroSupplyListForm() {
		//System.out.println("공급사목록");
		mav = mse.SupplyListCtl();
		return mav;
	}
	
	@PostMapping("/mroClientListForm")
	public ModelAndView morClientListForm() {
		//System.out.println("고객사목록");
		mav = mse.ClientListCtl();
		return mav;
	}
	
	//공급사 jsp
	@GetMapping("/supplyStart")
		public ModelAndView supplyForm() {
		mav = new ModelAndView();
			mav.setViewName("MroHome");
			return mav;
		}
	
	
	//공급사 반품리스트 뽑아오는 메서드
	@PostMapping("/supplyReceiveRefundListForm")
	@ResponseBody
	public List<RequestOrderBean> supplyReceiveRefundListForm() {
		List<RequestOrderBean> list;
		System.out.println("공급사 반품리스트");
		list = ssin.supplyReceiveRefundListForm();
		return list;
	}
	
	//공급사 교환리스트 뽑아오는 메서드
	@PostMapping("/supplyReceiveExchangeListForm")
	@ResponseBody
	public List<RequestOrderBean> supplyReceiveExchangeListForm() {
		List<RequestOrderBean> list;
		System.out.println("공급사 교환리스트");
		list = ssin.supplyReceiveExchangeListForm();
		return list;
	}
	
	//supply 반품디테일
	@PostMapping("/supplyReceiveAsDetail")
	@ResponseBody
	public List<RequestOrderDetailBean> supplyReceiveAsDetail(@RequestBody RequestOrderBean ro){
		//System.out.println(mo);
		return ssin.supplyReceiveAsDetail(ro);
	}
	
	//supply 반품 응답
	@PostMapping("/supplyResponseRefund")
	@ResponseBody
	public String supplyResponseRefund(@RequestBody RequestOrderBean ro){
		//System.out.println(mo);
		return ssin.supplyResponseRefund(ro);
	}
	
	//supply 교환 응답
	@PostMapping("/supplyResponseExchange")
	@ResponseBody
	public String supplyResponseExchange(@RequestBody MroOrderBean mo){
		//System.out.println(mo);
		return ssin.supplyResponseExchange(mo);
	}
	
	//supply 검색결과
	@PostMapping("/supplySearchAs")
	@ResponseBody
	public List<MroOrderBean> supplySearchAs(@RequestBody MroOrderBean mo){
		
		return ssin.supplySearchAs(mo);
	}
	
	
	
//	@PostMapping("/mroOrderListForm")
//	public ModelAndView mroOrderListForm() {
//		//System.out.println("주문서 목록");
//		mav = mse.getWaitOrderListCtl();
//		return mav;
//	}
	
//	@PostMapping("/mroGetOrderDetail")
//	public ModelAndView getOrderDetail(@ModelAttribute OrderDetailBean od) {
//		System.out.println(od.getOD_OSCODE());
//		mav = mse.getOrderDetail(od);
//		return mav;
//	}

}
