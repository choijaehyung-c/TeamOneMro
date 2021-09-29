package mrone.teamone.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import mrone.client.service.ClientServiceEntrance;
import mrone.mro.service.MroServiceEntrance;
import mrone.supply.service.SupplyServiceEntrance;
import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.ClientOrderBean;
import mrone.teamone.beans.ClientOrderDecide;
import mrone.teamone.beans.DeliveryBean;
import mrone.teamone.beans.MroOrderBean;
import mrone.teamone.beans.MroOrderDetailBean;
import mrone.teamone.beans.ProductBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;
import mrone.teamone.beans.SupplyInfoBean;
import mrone.teamone.beans.SupplySearchBean;
import mrone.teamone.beans.TaxBean;
import mrone.teamone.utill.ProjectUtils;

@RestController
@RequestMapping("/vue")
public class RestApiController {
	@Autowired
	private SupplyServiceEntrance sse;
	@Autowired
	private ClientServiceEntrance cse;
	@Autowired
	private MroServiceEntrance mse;
	@Autowired
	private ProjectUtils pu;
	
	/* test */
	@PostMapping("/DeliveryTest")
    public List<DeliveryBean> deliveryTest(@RequestBody DeliveryBean db){
       return sse.deliveryTest(db);
    }
    @PostMapping("/Insertsdcode")
    public void insertsdcode(@RequestBody DeliveryBean db){
       //System.out.println(db);
       sse.insertsdcode(db);
    }
    
    @CrossOrigin
    @PostMapping("/clientOrderDecide")
    public String updOrderDecide(@RequestBody ClientOrderDecide cd) {
    	//System.out.println("in");
    	return cse.updOrderDecide(cd);
    } 
	
	   //해당 회사 상품 PC 가져오기
    @PostMapping("/SupplyAllProductList")
    public List<ProductBean> supplyAllProductList(){
       return sse.supplyAllProductList();
    }
	
	
	//신청한요청들 취소하기
	@PostMapping("/SupplyRequestCancel")
	public String supplyRequestCancel(@RequestBody ProductBean pb){
		return sse.supplyRequestCancel(pb);
	}
	
	
	//해당 회사 상품 PR AF 가져오기
	@PostMapping("/SupplyPRAFProductList")
	public List<ProductBean> supplyPRAFProductList(){
		return sse.supplyPRAFProductList();
	}
	
	//해당 회사 상품 MR DR DA 가져오기
	@PostMapping("/SupplyMRDRDAProductList")
	public List<ProductBean> supplyMRDRDAProductList(){
		return sse.supplyMRDRDAProductList();
	}
	
	@PostMapping("/supplyResponseOrder")
	public String supplyResponseOrder(@RequestBody RequestOrderBean ro) {
		return sse.supplyResponseOrder(ro);
	}
	
	
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
	      //System.out.println(sse.RequestWaitOrderListCtl());
	      return reList;
	   }

	   @PostMapping("/getSupplyReceiveClearOrderList")
	   public List<RequestOrderBean> supplyReceiveClearOrderList() {
	      //System.out.println(sse.RequestClearOrderListCtl());
	      return sse.RequestClearOrderListCtl();
	   }

	   @PostMapping("/getSupplyReceiveWaitOrderListD")
	   public List<RequestOrderDetailBean> supplyReceiveWaitOrderListD(@RequestBody String recode) {
	      // //System.out.println(recode);
	      //System.out.println(sse.RequestWaitOrderListCtlD(recode));
	      return sse.RequestWaitOrderListCtlD(recode);

	   }

	   @PostMapping("/getSupplyReceiveClearOrderListD")
	   public List<RequestOrderDetailBean> supplyReceiveClearOrderListD(@RequestBody String recode) {
	      //System.out.println("수주접수 코드 : " + recode);
	      //System.out.println(sse.RequestClearOrderListCtlD(recode));
	      return sse.RequestClearOrderListCtlD(recode);

	   }

	   // 수정1
	   @PostMapping("/getSupplyRefuseOrderList")
	   public List<RequestOrderBean> getSupplyRefuseOrderList() {
	      //System.out.println(sse.getSupplyRefuseOrderList());
	      return sse.getSupplyRefuseOrderList();

	   }

	   // 수정1
	   @PostMapping("/getSupplyRefuseOrderD")
	   public List<RequestOrderDetailBean> getSupplyRefuseOrderD(@RequestBody String recode) {
	      //System.out.println(sse.getSupplyRefuseOrderListDetail(recode));
	      return sse.getSupplyRefuseOrderListDetail(recode);

	   }

	   // 수정1
	   @PostMapping("/supplyGoDelivery") // 배송출고시작 => 출고버튼(배송준비중->배송중으로 upd)
	   public String supplyGoDelivery(@RequestBody String recode) {
	      //System.out.println(sse.supplyGoDelivery(recode));
	      return sse.supplyGoDelivery(recode);

	   }

	   // 수정1
	   @PostMapping("/getTrackDeliveryList") // 배송 상태 확인 (배송중인 주문코드들 확인)
	   public List<DeliveryBean> getTrackDeliveryList() {
	      //System.out.println(sse.getTrackDeliveryList());
	      return sse.getTrackDeliveryList();

	   }

	   // 수정1
	   @PostMapping("/getTrackDL") // 특정 주문코드 배송 위치 확인
	   public List<DeliveryBean> getTrackDL(@RequestBody String recode) {
	      //System.out.println(recode);
	      //System.out.println("배송추적: " + sse.getTrackDL(recode));
	      return sse.getTrackDL(recode);

	   }
	
	
	
	@PostMapping("/getDLlist")
	public List<DeliveryBean> getDLList() {
		List<DeliveryBean> reList = null;
		reList = sse.getDLlist();
		//System.out.println(sse.getDLlist());
		return reList;	
	}
	
	@PostMapping("/choiceDV")
	public String updateDL(@RequestBody List<DeliveryBean> list ) {
		String message = sse.updateDL(list.get(0));
		
		return message;
		
	}
	//수정1
	@PostMapping("/getTaxCL")
	public List<ClientInfoBean> getTaxCL() {
		List<ClientInfoBean> reList = null;
		reList = sse.getTaxCL();
	
		return reList;			
	}
	
	//세금계산서 고객사 정보기입 수정
	@PostMapping("/getchoiceCLInfo")
	public ClientInfoBean getchoiceCLInfo(@RequestBody String cl_code) {
		return sse.choiceCLInfoCtl(cl_code);	
	}
	
	//세금계산서 세션으로 공급사 정보 자동기입
	@PostMapping("/getChoiceSPInfo")
	public SupplyInfoBean getchoiceSPInfo() {	
		return sse.choiceSPInfoCtl();	
	}
	
	//세금계산서 거래목록
	@PostMapping("/getTaxDeal")
	public List<RequestOrderBean> getTaxdeal() {
		return  sse.getTaxdeal();			
	}
	
	@PostMapping("/getchoiceDillInfo")
	public List<RequestOrderDetailBean> getchoiceDillInfo(@RequestBody List<RequestOrderDetailBean> list ) {
		//System.out.println(sse.choiceDillInfoCtl(list.get(0)));
		return sse.choiceDillInfoCtl(list.get(0));	
	}
	
	
	//공급사 반품리스트 뽑아오는 메서드
	@PostMapping("/supplyReceiveRefundListForm")
	public List<RequestOrderBean> supplyReceiveRefundListForm() {
		return sse.supplyReceiveRefundListForm();
	}
	
	//공급사 교환리스트 뽑아오는 메서드
	@PostMapping("/supplyReceiveExchangeListForm")
	
	public List<RequestOrderBean> supplyReceiveExchangeListForm() {
		return sse.supplyReceiveExchangeListForm();
	}
	
	//supply 반품디테일
	@PostMapping("/supplyReceiveAsDetailR")
	public List<RequestOrderDetailBean> supplyReceiveAsDetailR(@ModelAttribute("re_code") String re_code){//re_code=""형태로 프론트에서전달
		return sse.supplyReceiveAsDetailRR(re_code);
	}
	//교환 디테일
	@PostMapping("/supplyReceiveAsDetailE")
	public List<RequestOrderDetailBean> supplyReceiveAsDetailE(@ModelAttribute("re_code") String re_code){//re_code=""형태로 프론트에서전달
		return sse.supplyReceiveAsDetailER(re_code);
	}
	
	//supply 반품 응답
	@PostMapping("/supplyResponseRefund")
	public String supplyResponseRefund(@RequestBody RequestOrderBean ro){
		//System.out.println("test3");
		////System.out.println(mo);
		return sse.supplyResponseRefund(ro);
	}
	
	//supply 교환 응답
	/*
	 * @PostMapping("/supplyResponseExchange")
	 * 
	 * public String supplyResponseExchange(@RequestBody RequestOrderBean ro){
	 * ////System.out.println(mo); return null; }
	 */
	
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
		////System.out.println(pd + "ddd");
		return sse.supplyProductList(pd);
	}
	
	//supply 검색어로 물품가져옴
	@PostMapping("/supplySearchProduct")
	public List<ProductBean> supplySearchProduct(@RequestBody ProductBean pd){
		//System.out.println(pd.getWord());
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
	
	@CrossOrigin
	@PostMapping("/clientOrder")
	public List<String> clientOrderApi(@RequestBody ClientOrderBean co){
		return cse.clientRequestOrder(co);
	}
	
	@CrossOrigin
	@PostMapping("/clientRefund")
	public List<String> clientRefundApi(@RequestBody ClientOrderBean co){	
		return cse.clientRequestRefund(co);
	}
	
	@CrossOrigin
	@PostMapping("/clientExchange")
	public List<String> clientExchangeApi(@RequestBody ClientOrderBean co){
		return cse.clientRequestExchange(co);
	}
	
	@CrossOrigin
	@PostMapping("/clientGetTaxbill")
	public List<TaxBean> clientGetTaxbillApi(@ModelAttribute ClientInfoBean ci) throws Exception {
		return cse.clientGetTaxbill(ci);
	}
	
	@CrossOrigin
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
			////System.out.println("Restcontroller진입");
			
			return mse.getWaitOrderListCtl();
			
		}
		
		//주문대기 상세보기
		@PostMapping("/mroGetOrderDetail")
		public List<MroOrderDetailBean> mroGetOrderDetail(@RequestBody String osCode){
			//System.out.println(osCode);
			
			return mse.getOrderDetail(osCode);
			
		}
		
		//반품요청 리스트 받아오기
		@PostMapping("/mroRefundListForm")
		public List<MroOrderBean> mroRefundListForm(){
			////System.out.println("반품요청");
			
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
			////System.out.println("교환요청");
			
			return mse.getExchangeListCtl();
		}
		
		//교환 요청 상세보기
		@PostMapping("/mroGetExchangeDetail")
		public List<MroOrderDetailBean> mroGetExchangeDetail(@RequestBody String osCode){
		//System.out.println("교환요청 디테일");
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
			//System.out.println("MroGetNewProductDetail        진입");
			//System.out.println(pb.getPr_code()+ "RAI");
			return mse.mroGetNewProductDetail(pb);
		}
		
		//상품등록 수락 거절 응답 업데이트
		@PostMapping("/MroResponseNewProduct")
		public String mroResponseNewProduct(@RequestBody ProductBean pb){
			//System.out.println(pb.getPr_code());
			//System.out.println(pb.getPr_stcode());
			//System.out.println("mroResponseNewProduct 진입");
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
			//System.out.println("mroResponseNewProduct 진입");
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
		/*
		 * @PostMapping("/GetCate") public List<ProductBean> getCate(){ return
		 * sse.getCate(); }
		 */
		//추가할 상품정보 정보 보내기
		@PostMapping("/SupplyRequestNewProduct")
		public String supplyRequestNewProduct(@ModelAttribute ProductBean pb,HttpServletRequest req){
			System.out.println(pb.getFile().getOriginalFilename());
			
			//sse.supplyRequestNewProduct(pb);
			//here
			System.out.println(pb);
			return pu.setFile(pb.getFile(),req);
		}
	
		
		@GetMapping(
				  value = "/getImage",
				  produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_GIF_VALUE}
				)
		public @ResponseBody byte[] getImageWithMediaType(HttpServletRequest req,@RequestParam("file") String file) throws IOException {
			String lc = req.getSession().getServletContext().getRealPath("/")+".."+File.separator+".."+File.separator+".."+File.separator+"img"+File.separator;
		    System.out.println(lc);
		    System.out.println(file);
			InputStream in = new FileInputStream(lc+file);
			byte[] img = IOUtils.toByteArray(in);
			in.close();
		    return img;
		}
		

		// new
		@PostMapping("/searchSupply")
		public List<SupplyInfoBean> mroSearchSupplyList(@RequestBody String word) {
			//System.out.println("supply : " + word);
			//System.out.println(mse.mroSearchSupplyList(word));
			return mse.mroSearchSupplyList(word);
		}

		// new
		@PostMapping("/searchClient")
		public List<ClientInfoBean> mroSearchClientList(@RequestBody String word) {
			//System.out.println("고객사 : " + word);
			//System.out.println(mse.mroSearchSupplyList(word));
			return mse.mroSearchClientList(word);
		}

		// new
		@PostMapping("/delClient")
		public String mroDelClient(@RequestBody String code) {
			//System.out.println("고객사 : " + code);

			return mse.mroDelClient(code);
		}

		// new
		@PostMapping("/delSupply")
		public String mroDelSupply(@RequestBody String code) {
			//System.out.println("공급사 : " + code);

			return mse.mroDelSupply(code);
		}


		/////0914
		//거래내역가져오기
		@PostMapping("/getSupplyDealList")
		public List<RequestOrderBean> getSupplyDealList() {
			return sse.getSupplyDealList();			
		}

		//거래내역디테일
		@PostMapping("/getSupplyDealDetail")
		public List<RequestOrderDetailBean> getSupplyDealDetail(@RequestBody String re_code) {
			return sse.getSupplyDealDetail(re_code);			
		}
		
		//검색 수정
		@PostMapping("/getSearchSupplyDeal")
			public List<SupplySearchBean> getSearchSupplyDeal(@RequestBody String word) {
				return sse.getSearchSupplyDeal(word);			
			}
		
		//세금계산서인서트
		@PostMapping("/issueTax")
		public String issueTax(@RequestBody TaxBean tb) {
			
			return sse.issueTax(tb);			
		}
		
		//발행된 세금계산서 가져오기
		@PostMapping("/getIssuedTax")
		public List<TaxBean> getIssuedTax() {
			
			return sse.getIssuedTax();			
		}
		
		//세금계산서 디테일
		@PostMapping("/getIssuedTaxDetail")
		public TaxBean getIssuedTaxDetail(@RequestBody String tbcode) {		
			return sse.getIssuedTaxDetail(tbcode);			
		}
		
		@PostMapping("/getChart")
		public List<RequestOrderDetailBean> getChart() {
				//System.out.println("rest :"+sse.getChart());
			return sse.getChart();
		}

}
