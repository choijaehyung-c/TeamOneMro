package mrone.mro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrone.teamone.beans.ProductBean;

@Service
public class MroServiceCtlHSM {

	@Autowired
	MroDaoHSM hdao;

	public List<ProductBean> getRequestRegisterNewProductList() {
		List<ProductBean> PBList;
		PBList = hdao.getRequestRegisterNewProductList();
		
		return PBList;
	}

	
}
