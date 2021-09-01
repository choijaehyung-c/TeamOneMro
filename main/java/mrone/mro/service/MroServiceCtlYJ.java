package mrone.mro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import mrone.teamone.beans.SupplyInfoBean;

@Service
public class MroServiceCtlYJ {
	
	@Autowired
	MroDaoYJ dao;
	
	private ModelAndView mav = null;
	
	List<SupplyInfoBean> SupplyList() {
		List<SupplyInfoBean> list;
		list = dao.getSupplyList();
		System.out.println(list);
		
		return list;
	}

}
