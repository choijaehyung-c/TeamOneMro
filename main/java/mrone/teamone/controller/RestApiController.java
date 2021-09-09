package mrone.teamone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import mrone.client.service.ClientServiceEntrance;
import mrone.mro.service.MroServiceEntrance;
import mrone.supply.service.SupplyServiceEntrance;
import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.ClientOrderBean;
import mrone.teamone.beans.DeliveryBean;
import mrone.teamone.beans.MroOrderBean;
import mrone.teamone.beans.MroOrderDetailBean;
import mrone.teamone.beans.ProductBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;
import mrone.teamone.beans.SupplyInfoBean;
import mrone.teamone.beans.TaxBean;

@RestController
@RequestMapping("/vue")
public class RestApiController {
	@Autowired
	private SupplyServiceEntrance sse;
	@Autowired
	private ClientServiceEntrance cse;
	@Autowired
	private MroServiceEntrance mse;
	
	@GetMapping("/supplyReceiveWaitOrderListForm")
	public ModelAndView supplyReceiveWaitOrderListForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("supplyhomeNSB");
		return mav;

	}
	
	@GetMapping("/issueTaxBillForm")
	public ModelAndView issueTaxBillForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("issueTaxBillForm");
		return mav;

	}
	
	@PostMapping("/getSupplyReceiveWaitOrderList")
	public List<RequestOrderBean> supplyReceiveWaitOrderList() {
		List<RequestOrderBean> reList = null;
		reList = sse.RequestWaitOrderListCtl();
		System.out.println(sse.RequestWaitOrderListCtl());
		return reList;	
	}
	
	@PostMapping("/getSupplyReceiveClearOrderList")
	public List<RequestOrderBean> supplyReceiveClearOrderList() {
		List<RequestOrderBean> reList = null;
		reList = sse.RequestClearOrderListCtl();
		System.out.println(sse.RequestClearOrderListCtl());
		return reList;	
	}
	
	@PostMapping("/getSupplyReceiveWaitOrderListD")
	public List<RequestOrderBean> supplyReceiveWaitOrderListD(@RequestBody List<RequestOrderBean> list ) {
		
		System.out.println(sse.RequestWaitOrderListCtlD(list.get(0)));
		return sse.RequestWaitOrderListCtlD(list.get(0));
		
	}
	
	@PostMapping("/getSupplyReceiveClearOrderListD")
	public List<RequestOrderBean> supplyReceiveClearOrderListD(@RequestBody List<RequestOrderBean> list ) {
		
		System.out.println(sse.RequestClearOrderListCtlD(list.get(0)));
		return sse.RequestClearOrderListCtlD(list.get(0));
		
	}
	
	@PostMapping("/responseOrder")
	public String responseOrder(@RequestBody List<RequestOrderDetailBean> list ) {
		String message = sse.responseOrder(list.get(0));
		
		return message;
		
	}
	
	@PostMapping("/getDLlist")
	public List<DeliveryBean> getDLList() {
		List<DeliveryBean> reList = null;
		reList = sse.getDLlist();
		System.out.println(sse.getDLlist());
		return reList;	
	}
	
	@PostMapping("/choiceDV")
	public String updateDL(@RequestBody List<DeliveryBean> list ) {
		String message = sse.updateDL(list.get(0));
		
		return message;
		
	}
	
	@PostMapping("/getTaxCL")
	public List<ClientInfoBean> getTaxCL() {
		List<ClientInfoBean> reList = null;
		reList = sse.getTaxCL();
	
		return reList;			
	}
	
	@PostMapping("/getchoiceCLInfo")
	public List<ClientInfoBean> getchoiceCLInfo(@RequestBody List<ClientInfoBean> list ) {
		System.out.println(sse.choiceCLInfoCtl(list.get(0)));
		return sse.choiceCLInfoCtl(list.get(0));	
	}
	
	@PostMapping("/getChoiceSPInfo")
	public List<SupplyInfoBean> getchoiceSPInfo(@RequestBody List<SupplyInfoBean> list ) {
		System.out.println(sse.choiceSPInfoCtl(list.get(0)));
		return sse.choiceSPInfoCtl(list.get(0));	
	}
	
	@PostMapping("/getTaxDill")
	public List<RequestOrderBean> getTaxDill() {
		List<RequestOrderBean> reList = null;
		reList = sse.getTaxDill();
		System.out.println(sse.getTaxDill());
		return reList;			
	}
	
	@PostMapping("/getchoiceDillInfo")
	public List<RequestOrderDetailBean> getchoiceDillInfo(@RequestBody List<RequestOrderDetailBean> list ) {
		System.out.println(sse.choiceDillInfoCtl(list.get(0)));
		return sse.choiceDillInfoCtl(list.get(0));	
	}
	
	
	//공급사 반품리스트 뽑아오는 메서드
	@PostMapping("/supplyReceiveRefundListForm")
	
	public List<RequestOrderBean> supplyReceiveRefundListForm() {
		List<RequestOrderBean> list;
		System.out.println("공급사 반품리스트");
		list = sse.supplyReceiveRefundListForm();
		return list;
	}
	
	//공급사 교환리스트 뽑아오는 메서드
	@PostMapping("/supplyReceiveExchangeListForm")
	
	public List<RequestOrderBean> supplyReceiveExchangeListForm() {
		List<RequestOrderBean> list;
		System.out.println("공급사 교환리스트");
		list = sse.supplyReceiveExchangeListForm();
		return list;
	}
	
	//supply 반품디테일
	@PostMapping("/supplyReceiveAsDetail")
	
	public List<RequestOrderDetailBean> supplyReceiveAsDetail(@ModelAttribute("re_code") String re_code){//re_code=""형태로 프론트에서전달
		//System.out.println(re_code);
		return sse.supplyReceiveAsDetail(re_code);
	}
	
	//supply 반품 응답
	@PostMapping("/supplyResponseRefund")
	
	public String supplyResponseRefund(@RequestBody RequestOrderBean ro){
		//System.out.println(mo);
		return sse.supplyResponseRefund(ro);
	}
	
	//supply 교환 응답
	@PostMapping("/supplyResponseExchange")
	
	public String supplyResponseExchange(@RequestBody RequestOrderBean ro){
		//System.out.println(mo);
		return sse.supplyResponseExchange(ro);
	}
	
	//supply 검색결과
	@PostMapping("/supplySearchAs")
	
	public List<RequestOrderBean> supplySearchAs(@RequestBody RequestOrderBean re){
		
		return sse.supplySearchAs(re);
	}
	
	//supply 카테고리를 위한 페이지 이동
	@GetMapping("/supplyGetCategoryForm")
	public String supplyGetCategoryForm(){
		
		return "MroHome";
	}
	
	//supply 카테고리를 불러옴
	@PostMapping("/supplyGetCategory")
	
	public List<ProductBean> supplyGetCategory(){
		
		return sse.supplyGetCategory();
	}
	
	//supply 카테고리 물품 받아옴
	@PostMapping("/supplyProductList")
	
	public List<ProductBean> supplyProductList(@RequestBody ProductBean pd){
		//System.out.println(pd + "ddd");
		return sse.supplyProductList(pd);
	}
	
	//supply 검색어로 물품가져옴
	@PostMapping("/supplySearchProduct")
	public List<ProductBean> supplySearchProduct(@RequestBody ProductBean pd){
		System.out.println(pd.getWord());
		return sse.supplySearchProduct(pd);
	}
	
	@PostMapping("/mroSupplyListForm")
	public List<SupplyInfoBean> mroSupplyListForm() {
		return mse.SupplyListCtl();
	}
	
	@PostMapping("/mroClientListForm")
	public List<ClientInfoBean> morClientListForm() {

		return mse.ClientListCtl();
	}
	//---
	@PostMapping("/clientOrder")
	public String clientOrderApi(@RequestBody ClientOrderBean co){
		return cse.clientRequestOrder(co);
	}
	
	@PostMapping("/clientRefund")
	public String clientRefundApi(@RequestBody ClientOrderBean co){	
		return cse.clientRequestRefund(co);
	}
	
	@PostMapping("/clientExchange")
	public String clientExchangeApi(@RequestBody ClientOrderBean co){
		return cse.clientRequestExchange(co);
	}
	
	@PostMapping("/clientGetTaxbill")
	public List<TaxBean> clientGetTaxbillApi(@ModelAttribute ClientInfoBean ci) throws Exception {
		return cse.clientGetTaxbill(ci);
	}
	
	@PostMapping("/clientGetTaxbillDetail")
	public TaxBean clientGetTaxbillDetailApi(@ModelAttribute ClientInfoBean ci) throws Exception {
		return cse.clientGetTaxbillDetail(ci);
	}
	
	@PostMapping("/getSupplyCateProductList")
	public List<ProductBean> getSupplyCateProductList(@RequestBody ProductBean pr){
		return sse.getSupplyCateProductList(pr);
	}
	//--
	
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
		
		//반품요청 상세보기
		@PostMapping("/mroGetRefundDetail")
		public List<MroOrderDetailBean> mroGetRefundDetail(@RequestBody String osCode){
			
			return mse.getOrderDetail(osCode);
			
		}
		
		
		//교환요청 리스트 받아오기
		@PostMapping("/mroExchangeListForm")
		public List<MroOrderBean> mroExchangeListForm(){
			//System.out.println("교환요청");
			
			return mse.getExchangeListCtl();
		}
		
		//교환 요청 상세보기
		@PostMapping("/mroGetExchangeDetail")
		public List<MroOrderDetailBean> mroGetExchangeDetail(@RequestBody String osCode){
		System.out.println("교환요청 디테일");
			return mse.getOrderDetail(osCode);		
		}
	//--
		
		//새 상품 등록 요청리스트 가져오기
		@PostMapping("/GetRequestRegisterNewProductList")
		public List<ProductBean> getRequestRegisterNewProductList(){
			return mse.getRequestRegisterNewProductList();
		}
		
		
		// 새 물건 등록상품 디테일 가져오기
		@PostMapping("/MroGetNewProductDetail")
		public ProductBean mroGetNewProductDetail(@RequestBody ProductBean pb){
			System.out.println("MroGetNewProductDetail        진입");
			System.out.println(pb.getPr_code()+ "RAI");
			return mse.mroGetNewProductDetail(pb);
		}
		
		//상품등록 수락 거절 응답 업데이트
		@PostMapping("/MroResponseNewProduct")
		public String mroResponseNewProduct(@RequestBody ProductBean pb){
			System.out.println(pb.getPr_code());
			System.out.println(pb.getPr_stcode());
			System.out.println("mroResponseNewProduct 진입");
			return mse.mroResponseNewProduct(pb);
		}
		
		//수정요청 리스트 불러오기
		@PostMapping("/CallModifyRequestList")
		public List<ProductBean> callModifyRequestList(){
			return mse.callModifyRequestList();
		}
		
		//해당 수정요청 디테일 가져오기
		@PostMapping("/MroGetModifyProductDetail")
		public ProductBean mroGetModifyProductDetail(@RequestBody ProductBean pb){
			return mse.mroGetModifyProductDetail(pb);
		}
		
		
		// 상품수정 수락 거절 응답 업데이트
		@PostMapping("/MroResponseModifyProduct")
		public String mroResponseModifyProduct(@RequestBody ProductBean pb){
			System.out.println("mroResponseNewProduct 진입");
			return mse.mroResponseModifyProduct(pb);
		}

		
		// 서플라이 
		
		//물건 디테일 가져오기
		@PostMapping("/SupplyGetProductDetail")
		public ProductBean supplyGetProductDetail(@RequestBody ProductBean pb){
			return sse.supplyGetProductDetail(pb);
		}
		
		//수정할 물품 입력정보 요청넣기
		@PostMapping("/SupplyRequestModify")
		public String supplyRequestModify(@RequestBody ProductBean pb){
			return sse.supplyRequestModify(pb);
		}
		
		//수량 업데이트
		@PostMapping("/SupplyModifyStock")
		public String supplyModifyStock(@RequestBody ProductBean pb){
			return sse.supplyModifyStock(pb);
		}
		
		//상품 삭제요청
		@PostMapping("/SupplyRequestDelete")
		public String supplyRequestDelete(@RequestBody ProductBean pb){
			return sse.supplyRequestDelete(pb);
		}
		
		//새 상품 등록요청시 카테고리 가져오기
		@PostMapping("/GetCate")
		public List<ProductBean> getCate(){
			return sse.getCate();
		}
		
		//추가할 상품정보 정보 보내기
		@PostMapping("/SupplyRequestNewProduct")
		public String supplyRequestNewProduct(@ModelAttribute ProductBean pb){
			/*
			 * System.out.println(pb.getFile().getOriginalFilename());
			 * System.out.println(pu.setFile(pb.getFile()));
			 */
			return null/* sse.supplyRequestNewProduct(pb) */;
		}
	
}
