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
		System.out.println(pb.getPr_stcode()+ "mro");

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



		if(pb.getPr_stcode().equals("PC")) {//수정요청 수락
			if(hdao.updatePCToPD(pb)) {//해당 상품코드 AND PC인 애를 PD로
				if(hdao.mroResponseModifyProduct(pb)) {//해당상품코드 AND MR인 애를 PC로		
					tran = true;
				}
			}
		}else if(pb.getPr_stcode().equals("RF")){ //수정요청 거절
			if(hdao.stcodeCheck(pb)) { // 해당 코드and stcode가 RF인 컬럼이 있는지 체크
				if(hdao.deleteStcode(pb)) {// 해당코드의 stcode가 RF인 컬럼을 삭제
					if(hdao.mroResponseModifyProduct(pb)){//해당 상품코드AND MR인 애를 RF로			
						tran = true;
					}					
				}
			}else {
				if(hdao.mroResponseModifyProduct(pb)){//해당 상품코드AND MR인 애를 RF로			
					tran = true;
				}	
			}


		}else if(pb.getPr_stcode().equals("DA")) {//삭제요청 수락
			if(hdao.mroResponseDeleteProduct(pb)) {// 해당 삭제요청 상품코드 AND DR인 애를 DA로 
				if(hdao.updatePCToPD(pb)) { //해당 삭제요청 상품코드 AND PC인 애를 PD로
					tran = true;
				}
			}
		}else {//삭제요청 거절
			if(hdao.stcodeCheck(pb)) {// 해당 코드and stcode가 DF인 컬럼이 있는지 체크
				if(hdao.deleteStcode(pb)) {// 해당코드의 stcode가 DF인 컬럼을 삭제
					if(hdao.mroResponseDeleteProduct(pb)) {// 해당 삭제요청 상품코드 AND DR인 애를 DF로
						tran = true;
					}
				}
			}else {
				hdao.mroResponseDeleteProduct(pb);
				tran = true;
			}
		}

		pu.setTransactionResult(tran);

		message = tran? "success!": "fail!";

		return message;
	}


}
