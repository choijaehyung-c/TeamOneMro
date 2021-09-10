package mrone.supply.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;

import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.DeliveryBean;
import mrone.teamone.beans.MroOrderDetailBean;
import mrone.teamone.beans.ProductBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;
import mrone.teamone.beans.SupplyInfoBean;
import mrone.teamone.beans.SupplySearchBean;
import mrone.teamone.utill.Encryption;
import mrone.teamone.utill.ProjectUtils;


@Service
class SupplyServiceCtl {
	@Autowired
	SupplyDao dao;
	@Autowired
	ProjectUtils pu;
	@Autowired
	Encryption enc;

	List<RequestOrderBean> getSupplyDealListCtl(String re_spcode) {
		return dao.getSupplyDealList(re_spcode);
	}

	RequestOrderBean getSupplyDealDetailCtl(String re_code) {
		return dao.getSupplyDealDetail(re_code);
	}

	List<SupplySearchBean> getSearchSupplyDealCtl(String word) {
		return dao.getSearchSupplyDeal(word);
	}

	List<ProductBean> getSupplyCateProductList(ProductBean pb) {
		return dao.getSupplyCateProductList(pb);
	}

	List<RequestOrderBean> waitOrderlist() {

		List<RequestOrderBean> reList = null;
		reList = dao.waitOrderlist();
		return reList;
	}

	List<RequestOrderBean> clearOrderlist() {

		List<RequestOrderBean> reList = null;
		reList = dao.clearOrderlist();
		return reList;
	}

	List<RequestOrderBean> waitOrderlistD(RequestOrderBean rb) {

		List<RequestOrderBean> reList = null;
		reList = dao.waitOrderlistD(rb);
		return reList;
	}

	List<RequestOrderBean> clearOrderlistD(RequestOrderBean rb) {

		List<RequestOrderBean> reList = null;
		reList = dao.clearOrderlistD(rb);
		return reList;
	}

	String responseOrder(RequestOrderDetailBean rdb) {
		String message = null;
		rdb.setRd_recode(rdb.getRd_recode());
		for (int i = 0; i < rdb.getRd_recode().length(); i++) {
			if (dao.responseOrder(rdb)) {
				if (dao.responseOrder2(rdb)) {
					message = "접수완료";
				}
			}
		}
		dao.insertDL(rdb);
		return message;
	}

	List<DeliveryBean> getDLlist() {
		List<DeliveryBean> reList = null;
		reList = dao.getDLlist();
		return reList;
	}

	String updateDL(DeliveryBean db) {
		String message = null;
		db.setDl_code(db.getDl_code());
		db.setDl_dvcode(db.getDl_dvcode());
		if (dao.updateDL(db)) {
			message = "배정완료";
		}

		return message;
	}

	List<ClientInfoBean> getTaxCL() {
		List<ClientInfoBean> reList = null;
		reList = dao.getTaxCL();
		return reList;
	}

	List<ClientInfoBean> choiceCLInfo(ClientInfoBean cb) {
		List<ClientInfoBean> reList = null;
		reList = dao.choiceCLInfo(cb);
		return reList;
	}

	List<SupplyInfoBean> choiceSPInfo(SupplyInfoBean sb) {
		List<SupplyInfoBean> reList = null;
		reList = dao.choiceSPInfo(sb);
		return reList;
	}

	List<RequestOrderBean> getTaxDill() {
		List<RequestOrderBean> reList = null;
		reList = dao.getTaxDill();
		return reList;
	}

	List<RequestOrderDetailBean> choiceDillInfo(RequestOrderDetailBean rdb) {
		List<RequestOrderDetailBean> reList = null;
		reList = dao.choiceDillInfo(rdb);
		return reList;
	}
//------------------------------------------------------------
	ProductBean supplyGetProductDetail(ProductBean pb) {
		return dao.supplyGetProductDetail(pb);
	}

	String supplyRequestModify(ProductBean pb) {
		System.out.println(pb);
		String message = "";
		System.out.println("111111");
		// 해당 상품코드의 PR_STCODE에 MR인 애가 있는지 확인
		if (dao.MRCheck(pb)) {
			System.out.println("22222222");
			if (dao.updateSupplyRequestModify(pb)) {
				System.out.println("33333333");
				message = "success";
			} else {
				System.out.println("44444444");
				message = "fail";
			}

		} else {
			System.out.println("5555555");
			if (dao.supplyRequestModify(pb)) {
				System.out.println("6666666");
				message = "success";
			} else {
				System.out.println("777777");
				message = "fail";
			}

		}

		return message;
	}

	String supplyModifyStock(ProductBean pb) {
		String message = "";
		if (dao.supplyModifyStock(pb)) {
			message = "success";
		} else {
			message = "fail";
		}
		return message;
	}

	String supplyRequestDelete(ProductBean pb) {
		System.out.println(pb);
		String message = "";
		if (dao.MRCheck(pb)) {
			// 이미 PR_STCODE에 DR이 있을경우 그냥 업데이트
			if (dao.updateSupplyRequestModify(pb)) {
				message = "success";
			} else {
				message = "fail";
			}

		} else {

			if (dao.supplyRequestDelete(pb)) {
				message = "success";
			} else {
				message = "fail";
			}
		}
		return message;
	}

	List<ProductBean> getCate() {
		return dao.getCate();
	}

	String supplyRequestNewProduct(ProductBean pb) {
		String message = "";
		pb.setPr_spcode("KR001G");
		pb.setPr_code("2002002002");
		pb.setPr_tax(String.valueOf(Integer.parseInt(pb.getPr_price()) / 10));
		pb.setPr_spbkind("KL");
		pb.setPr_stcode("PR");
		System.out.println(pb);
		if (dao.supplyRequestNewProduct(pb)) {
			message = "success";
		} else {
			message = "fail";
		}

		return message;
	}
//------------------------------------------------------------
	//수정완료 re에 st가 RR인 주문서 리턴 
	List<RequestOrderBean> supplyReceiveRefundListForm() {
		RequestOrderBean ro= new RequestOrderBean();
		try {
			ro.setRe_spcode(enc.aesDecode((String)pu.getAttribute("type"),enc.aesDecode((String)pu.getAttribute("userSs"),"session")));
			ro.setRe_state("RR");
		} catch (Exception e) {e.printStackTrace();}
		return dao.getReceiveAsListSp(ro);
	}
	//수정완료 re에 st가 ER인 주문서 리턴
	List<RequestOrderBean> supplyReceiveExchangeListForm() {
		RequestOrderBean ro= new RequestOrderBean();
		try {
			ro.setRe_spcode(enc.aesDecode((String)pu.getAttribute("type"),enc.aesDecode((String)pu.getAttribute("userSs"),"session")));
			ro.setRe_state("ER");
		} catch (Exception e) {e.printStackTrace();}
		return dao.getReceiveAsListSp(ro);
	}

	//수정완료 반품 또는 교환 디테일
	List<RequestOrderDetailBean> supplyReceiveAsDetail(String re_code){
		List<RequestOrderDetailBean> list = dao.supplyReceiveAsDetail(re_code);
		for(int i=0;i<list.size();i++) {list.get(i).setPr_ttprice(list.get(i).getPr_price()+list.get(i).getPr_tax());}
		return list;
	}

	//공급사 - 반품에 대한 응답(거절(FF)or수락(RC))
	String supplyResponseRefund(String re_code) {
		//수락
		//들어온 re_code에 RR 코드 -> '반품수락' 주문서,발주서 업데이트 os od re rd
		//들어온 re_code에 RR이 아닌 코드로 새 (주문서,발주서) 인설트 os od re rd // 해당 새 주문코드에 운송장
		//오리진 주문서 st코드 폐기처리 os od re rd
		
		//거절
		//들어온 re_code에 RR 코드 -> '반품거절' 주문서,발주서 업데이트 os re / od rd ->   거절사유 입력 
		return null;
	}

	String supplyResponseExchange(String re_code) {
		//수락
		//들어온 re_code에 ER 코드 -> '교환수락' 주문서,발주서 업데이트 os od re rd
		//해당 새 주문코드에 운송장
		
		//거절
		//들어온 re_code에 ER 코드 -> '반품거절' 주문서,발주서 업데이트 os re / od rd ->   거절사유 입력 
		
		return null;
	}


	//검색결과
	List<RequestOrderBean> supplySearchAs(RequestOrderBean re) {
		List<RequestOrderBean> list;
		list = dao.supplySearchAs(re);

		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getRe_state().equals("OR")) {
				list.get(i).setRe_state("주문요청");
			}if(list.get(i).getRe_state().equals("RR")) {
				list.get(i).setRe_state("반품요청");
			}if(list.get(i).getRe_state().equals("ER")) {
				list.get(i).setRe_state("교환요청");
			}if(list.get(i).getRe_state().equals("OC")) {
				list.get(i).setRe_state("구매확정");
			}if(list.get(i).getRe_state().equals("OA")) {
				list.get(i).setRe_state("주문수락");
			}

		}


		return list;
	}
	
	List<ProductBean> supplyGetCategory() {
		return dao.supplyGetCategory();
	}

	List<ProductBean> supplyProductList(ProductBean pd) {
		pd.setPr_spcode("KR001D");
		return dao.supplyProductList(pd);
	}

	List<ProductBean> supplySearchProduct(ProductBean pd) {
		pd.setPr_spcode("KR001D");
		return dao.supplySearchProduct(pd);
	}
	
	
	
	
	
	List<ProductBean> supplyAllProductList(){
		String spcode =null;
		try {
			spcode=enc.aesDecode((String)pu.getAttribute("type"),enc.aesDecode((String)pu.getAttribute("userSs"),"session"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.supplyAllProductList(spcode);
	}
	
	
	
}
