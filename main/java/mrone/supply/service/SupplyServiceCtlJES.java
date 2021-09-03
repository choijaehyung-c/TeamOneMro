package mrone.supply.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import mrone.teamone.beans.ProductBean;


@Service
public class SupplyServiceCtlJES {
	ModelAndView mav;
	@Autowired
	SqlSessionTemplate sqlSession;
	@Autowired
	SupplyDaoJES sdjes;
	
	//상품리스트 전체 불러오기
	public List<ProductBean> supplyProductListCtl() {
		List<ProductBean> prlist;
		prlist = sdjes.insProdcutList();
		
		return prlist;
	}
	//상품 클릭시 상세 보이기
	public List<ProductBean> productListDetail(String prcode){
		List<ProductBean> prcod;
		prcod = sdjes.insProdcutListDitail(prcode);
		System.out.println(prcod);
		return prcod;
	}
	
	//상품 재고 수정
	//public SupplyModifyStockCtl(){}
	
	//새상품리스트(새상품은 코드로 구분)현재는 상품 all리스트
	public List<ProductBean> NewSupplyPrListCtl(){
		List<ProductBean> nprlist;
		nprlist = sdjes.insProdcutList();
		
		return nprlist;
	}
	//등록
	public String getSuReNewProduct(ProductBean pd) {
		return null;
	}
	//수정
	public String supplyReModifyCtl(ProductBean pd) {
		
		return null;
	}
	//삭제
	public String supplyReDeleteCtl() {
		return null;
	}
	
}