package mrone.teamone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrone.mro.service.MroServiceCtlHSM;
import mrone.teamone.beans.ProductBean;

@RestController
@RequestMapping("/HSM")
public class RestApiController4 {

	
	@Autowired
	MroServiceCtlHSM msch;
	
	//새 상품 등록 요청리스트 가져오기
	@PostMapping("/GetRequestRegisterNewProductList")
	public List<ProductBean> getRequestRegisterNewProductList(){
		//System.out.println("Restcontroller진입");
		return msch.getRequestRegisterNewProductList();
	}
	
	
	// 새 물건 등록상품 디테일 가져오기
	@PostMapping("/MroGetNewProductDetail")
	public ProductBean mroGetNewProductDetail(@RequestBody ProductBean pb){
		System.out.println("MroGetNewProductDetail        진입");
		System.out.println(pb.getPr_code()+ "RAI");
		return msch.mroGetNewProductDetail(pb);
	}
	
	//상품등록 수락 거절 응답 업데이트
	@PostMapping("/MroResponseNewProduct")
	public String mroResponseNewProduct(@RequestBody ProductBean pb){
		System.out.println(pb.getPr_code());
		System.out.println(pb.getPr_stcode());
		System.out.println("mroResponseNewProduct 진입");
		return msch.mroResponseNewProduct(pb);
	}
	
	//수정요청 리스트 불러오기
	@PostMapping("/CallModifyRequestList")
	public List<ProductBean> callModifyRequestList(){
		return msch.callModifyRequestList();
	}
	
	//해당 수정요청 디테일 가져오기
	@PostMapping("/MroGetModifyProductDetail")
	public ProductBean mroGetModifyProductDetail(@RequestBody ProductBean pb){
		return msch.mroGetModifyProductDetail(pb);
	}
	
	
	// 상품수정 수락 거절 응답 업데이트
	@PostMapping("/MroResponseModifyProduct")
	public String mroResponseModifyProduct(@RequestBody ProductBean pb){
		System.out.println("mroResponseNewProduct 진입");
		return msch.mroResponseModifyProduct(pb);
	}
}
