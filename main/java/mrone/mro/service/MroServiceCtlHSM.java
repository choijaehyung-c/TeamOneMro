package mrone.mro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;

import mrone.teamone.beans.ProductBean;
import mrone.teamone.utill.ProjectUtils;

@Service
public class MroServiceCtlHSM {

	@Autowired
	MroDaoHSM hdao;
	
	@Autowired
	ProjectUtils pu;

	public List<ProductBean> getRequestRegisterNewProductList() {
		return hdao.getRequestRegisterNewProductList();
	}

	public ProductBean mroGetNewProductDetail(ProductBean pb) {
		System.out.println(pb.getPr_code()+ "mro");
		
		return hdao.mroGetNewProductDetail(pb);
	}
	
	public String mroResponseNewProduct(ProductBean pb) {
		String message = null;
		if(hdao.mroResponseNewProduct(pb)) {
			message = "success!";
		}else {
			message = "fail!";
		}
		return message;
	}

	public  List<ProductBean> callModifyRequestList() {
		return hdao.callModifyRequestList();
	}

	public ProductBean mroGetModifyProductDetail(ProductBean pb) {
		return hdao.mroGetModifyProductDetail(pb);
	}

	
	public String mroResponseModifyProduct(ProductBean pb) {
		String message = null;
		boolean tran = false;
		pu.setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,
							  TransactionDefinition.ISOLATION_READ_COMMITTED,false);
		if(pb.getPr_stcode().equals("PC")) {
			if(hdao.updatePCToPD(pb)) {//해당 상품코드 AND PC인 애를 PD로
				if(hdao.mroResponseModifyProduct(pb)) {//해당상품코드 AND MR인 애를 PC로		
					tran = true;
				}
			}
		}else {
			if(hdao.mroResponseModifyProduct(pb)){//해당 상품코드AND MR인 애를 PD로   거절코드 따로			
				tran = true;
			}
		}
		
		pu.setTransactionResult(tran);
		
		message = tran? "success!": "fail!";
				
		return message;
	}

	
}
