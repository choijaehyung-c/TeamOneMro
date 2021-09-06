package mrone.supply.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrone.teamone.beans.ProductBean;
import mrone.teamone.utill.ProjectUtils;

@Service
public class SupplyServiceHSM {

	
	@Autowired
	SupplyDaoHSM dao;
	@Autowired
	ProjectUtils pu;

	
	public ProductBean supplyGetProductDetail(ProductBean pb) {
		return dao.supplyGetProductDetail(pb);
	}


	public String supplyRequestModify(ProductBean pb) {
		System.out.println(pb);
		String message = "";
		System.out.println("111111");
		//해당 상품코드의 PR_STCODE에 MR인 애가 있는지 확인
		if(dao.MRCheck(pb)) {
			System.out.println("22222222");
			if(dao.updateSupplyRequestModify(pb)) {
				System.out.println("33333333");
				message = "success";
			}else {
				System.out.println("44444444");
				message = "fail";
			}
			
		}else {
			System.out.println("5555555");
			if(dao.supplyRequestModify(pb)) {
				System.out.println("6666666");
				message = "success";
			}else {
				System.out.println("777777");
				message = "fail";
			}
			
		}
		
		return message;
	}


	public String supplyModifyStock(ProductBean pb) {
		String message = "";
			if(dao.supplyModifyStock(pb)) {
				message = "success";
			}else {
				message = "fail";
			}
		return message;
	}


	public String supplyRequestDelete(ProductBean pb) {
		System.out.println(pb);
		String message = "";
		if(dao.MRCheck(pb)) {
			// 이미 PR_STCODE에 DR이 있을경우 그냥 업데이트
			if(dao.updateSupplyRequestModify(pb)) {
				message = "success";
			}else {
				message = "fail";
			}
		
		}else {
			
			if(dao.supplyRequestDelete(pb)) {
				message = "success";
			}else {
				message = "fail";
			}
		}
		return message;
	}

	
	

}
