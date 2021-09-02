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
	

	@PostMapping("/GetRequestRegisterNewProductList")
	public List<ProductBean> getRequestRegisterNewProductList(){
		System.out.println("Restcontroller진입");
		
		return msch.getRequestRegisterNewProductList();
	}
	
	
	@PostMapping("/ResponseNewProduct")
	public void responseNewProduct(){
		System.out.println("responseNewProduct 진입");
		
	}
	
}
