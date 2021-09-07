package mrone.teamone.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mrone.mro.service.MroServiceCtlHSM;
import mrone.supply.service.SupplyServiceHSM;
import mrone.teamone.beans.ProductBean;
import mrone.teamone.utill.ProjectUtils;

@RestController
@RequestMapping("/HSM")
public class RestApiController4 {

	
	@Autowired
	MroServiceCtlHSM msch;
	
	@Autowired
	SupplyServiceHSM ssch;
	
	@Autowired
	ProjectUtils pu;
	
	//새 상품 등록 요청리스트 가져오기
	@PostMapping("/GetRequestRegisterNewProductList")
	public List<ProductBean> getRequestRegisterNewProductList(){
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
	
	
	
	
	
	
	
	// 서플라이 
	
	//물건 디테일 가져오기
	@PostMapping("/SupplyGetProductDetail")
	public ProductBean supplyGetProductDetail(@RequestBody ProductBean pb){
		return ssch.supplyGetProductDetail(pb);
	}
	
	//수정할 물품 입력정보 요청넣기
	@PostMapping("/SupplyRequestModify")
	public String supplyRequestModify(@RequestBody ProductBean pb){
		return ssch.supplyRequestModify(pb);
	}
	
	//수량 업데이트
	@PostMapping("/SupplyModifyStock")
	public String supplyModifyStock(@RequestBody ProductBean pb){
		return ssch.supplyModifyStock(pb);
	}
	
	//상품 삭제요청
	@PostMapping("/SupplyRequestDelete")
	public String supplyRequestDelete(@RequestBody ProductBean pb){
		return ssch.supplyRequestDelete(pb);
	}
	
	//새 상품 등록요청시 카테고리 가져오기
	@PostMapping("/GetCate")
	public List<ProductBean> getCate(){
		return ssch.getCate();
	}
	
	//추가할 상품정보 정보 보내기
	@PostMapping("/SupplyRequestNewProduct")
	public String supplyRequestNewProduct(@ModelAttribute ProductBean pb){
		System.out.println(pb.getFile().getOriginalFilename());
		System.out.println(pu.setFile(pb.getFile()));
		
		return null/* ssch.supplyRequestNewProduct(pb) */;
	}
}
