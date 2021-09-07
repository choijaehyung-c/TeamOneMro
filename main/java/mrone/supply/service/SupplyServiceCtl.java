package mrone.supply.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionDefinition;

import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.DeliveryBean;
import mrone.teamone.beans.MroOrderDetailBean;
import mrone.teamone.beans.ProductBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;
import mrone.teamone.beans.SupplyInfoBean;
import mrone.teamone.beans.SupplySearchBean;
import mrone.teamone.utill.ProjectUtils;

class SupplyServiceCtl {
	@Autowired
	SupplyDao dao;
	@Autowired
	ProjectUtils pu;

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
	List<RequestOrderBean> supplyReceiveRefundListForm() {
		RequestOrderBean re= new RequestOrderBean();
		List<RequestOrderBean> list;

		re.setRe_spcode("KR001D");//Session이 없어서 강제로 넣어줌
		re.setRe_state("RR");

		list = dao.getRefundListSp(re);

		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getRe_state().equals("RR")) {
				list.get(i).setRe_state("반품요청");
			}
		}

		//System.out.println(dao.getRefundListSp(mod));

		//Session			
		//		try {//Session이 살아있으면, Session에 있는 아이디를 mod에 담는다.
		//			if(pu.getAttribute("userSs")!=null) {
		//				mod.setOd_prspcode((String)pu.getAttribute("userSs"));
		//				dao.getRefundListSp(mod);
		//			}
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//		}

		return list;
	}

	List<RequestOrderBean> supplyReceiveExchangeListForm() {
		RequestOrderBean re= new RequestOrderBean();
		List<RequestOrderBean> list;

		re.setRe_spcode("KR001D");//Session이 없어서 강제로 넣어줌
		re.setRe_state("ER");

		list = dao.getRefundListSp(re);

		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getRe_state().equals("ER")) {
				list.get(i).setRe_state("교환요청");
			}
		}

		return list;
	}

	//반품 또는 교환 디테일
	List<RequestOrderDetailBean> supplyReceiveAsDetail(RequestOrderBean ro){
		List<RequestOrderDetailBean> list;

		list = dao.supplyReceiveAsDetail(ro);

		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getRd_stcode().equals("RR")) {
				list.get(i).setRd_stcode("반품요청");
			}else if(list.get(i).getRd_stcode().equals("OC")) {
				list.get(i).setRd_stcode("구매확정");
			}else if(list.get(i).getRd_stcode().equals("RC")){
				list.get(i).setRd_stcode("반품처리");
			}else if(list.get(i).getRd_stcode().equals("OR")) {
				list.get(i).setRd_stcode("주문요청");
			}else if(list.get(i).getRd_stcode().equals("ER")) {
				list.get(i).setRd_stcode("교환요청");
			}
		}

		return list;
	}

	//공급사 - 반품에 대한 응답(거절(FF)or수락(RC))
	String supplyResponseRefund(RequestOrderBean re) {
		String message="";
		String originCode= re.getRe_code();
		pu.setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED ,false);

		if(re.getRe_state().equals("PD")) {
			System.out.println("수락");

			try {
				//9. OD 테이블 : 오리지널 주문코드 RR-> PD(폐기)
				if(dao.supplyResponseRefund(re)) {
					//10. OS 테이블 : 오리지널 주문코드  RR->PD(폐기),
					if(dao.supplyResponseRefundOS(re)) {
						//11. RD 테이블: 오리지널 주문코드 RR->PD(폐기)
						if(dao.supplyResponseRefundRD(re)) {
							// 12. RE테이블:오리지널 주문 코드 RR->PD(폐기)
							if(dao.supplyResponseRefundRE(re)) {
								//1. os 테이블 : RR인 애들을 위한 새로운 주문코드 생성
								if(this.insNewOrdersRA(re)) {
									//2. od 테이블 : RR인애들을 위한 새로운 주문코드 생성
									if(this.insNewOdRA(re)) {
										//3. RE테이블 : RR인애들을 위한 새로운 주문코드 생성
										if(this.insNewRequestRA(re)) {
											//4. RD테이블 : RR인 애들을 위한 새로운 주문코드 생성
											if(this.insNewRdRA(re)) {
												System.out.println("os성공");
												//5.OS테이블 : OC인 애들을 위한 새로운 주문코드 생성
												if(this.insOrdersOC(re)) {
													//6. OD 테이블 : OC인 애들을 위한 새로운 주문코드 생성
													if(this.insNewOdOC(re)) {
														//7. RE테이블 : OC인 애들을 위한 새로운 주문코드 생성
														if(this.insNewRequestOC(re)) {
															//8. RD테이블 : OC인 애들을 위한 새로운 주문코드 생성
															if(this.insNewRdOC(re)) {
																message="반품요청이 정상적으로 처리되었습니다.";
																System.out.println("반품처리 완성");
																pu.setTransactionResult(true); // commit완료
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}catch (Exception e) {
				pu.setTransactionResult(false); //rollback
				System.out.println("업데이트 실패");
				message = "반품처리에 실패했습니다. 잠시후 다시 시도해주세요";
			}


		}else {
			//RR-> FF로 업데이트 -> 거절하면 다 FF가 되는데,,,그럼 세금계산서할때 FF도 조회해야함..OC로 바꿀수도없고..
			System.out.println(re);
			re.setRe_state("OC");
			if(dao.supplyResponseRefund(re)) {	
				if(dao.supplyResponseRefundOS(re)) {
					if(dao.supplyResponseRefundRD(re)) {
						if(dao.supplyResponseRefundRE(re)) {
							System.out.println("거절");
							message = "반품요청이 거절되었습니다.";							
						}
					}
				}
			}		
		}
		return message;
	}

	//8. RD테이블 : OC인 애들을 위한 새로운 주문코드 생성
	private boolean insNewRdOC(RequestOrderBean re) {
		List<MroOrderDetailBean> mod = new ArrayList<MroOrderDetailBean>();
		List<MroOrderDetailBean> rrList;
		boolean insert = false;

		re.setRe_state("OC");
		rrList = dao.selRequest(re);
		System.out.println(rrList);//RR인 애들만 뽑아옴.
		if(rrList!=null) {
			for(int i=0; i<rrList.size(); i++) {		
				//새로운 주문코드를 만든다. 그 주문코드의 oc의 정보들을 넣기 od,os인설트
				MroOrderDetailBean result  = new MroOrderDetailBean();
				result.setOd_oscode(dao.checkCount());
				result.setOd_prcode(rrList.get(i).getOd_prcode());
				result.setOd_prspcode(rrList.get(i).getOd_prspcode());
				result.setOd_stcode("OC");
				result.setOd_quantity(rrList.get(i).getOd_quantity());
				//note오면 note추가

				mod.add(result);
			}
		}else {
			System.out.println("새로운 주문번호 없음");
		}
		if(dao.insNewRd(mod)) {
			insert = true;
		}
		return insert;
	}

	//7. RE테이블 : OC인 애들을 위한 새로운 주문코드 생성
	private boolean insNewRequestOC(RequestOrderBean re) {
		List<MroOrderDetailBean> list;
		boolean insert = false;

		re.setRe_state("OC");
		list = dao.selRequest(re);
		if(list!=null) {
			re.setRe_oscode(dao.checkCount());
			re.setRe_clcode(list.get(0).getOs_clcode());	
			re.setRe_state("OC");
			re.setRe_spcode(list.get(0).getOd_prspcode());

			if(dao.insNewRequest(re)) {
				insert = true;
			}
		}else {
			System.out.println("새로운 주문번호 없음");
		}

		return insert;
	}

	//6.OD 테이블 : OC인 애들을 위한 새로운 주문코드 생성
	private boolean insNewOdOC(RequestOrderBean re) {
		List<MroOrderDetailBean> mod = new ArrayList<MroOrderDetailBean>();
		List<MroOrderDetailBean> rrList;
		boolean insert = false;

		re.setRe_state("OC");
		rrList = dao.selRequest(re);
		System.out.println(rrList);//RR인 애들만 뽑아옴.
		if(rrList!=null) {
			for(int i=0; i<rrList.size(); i++) {		
				//새로운 주문코드를 만든다. 그 주문코드의 oc의 정보들을 넣기 od,os인설트
				MroOrderDetailBean result  = new MroOrderDetailBean();
				result.setOd_oscode(dao.checkCount());
				result.setOd_prcode(rrList.get(i).getOd_prcode());
				result.setOd_prspcode(rrList.get(i).getOd_prspcode());
				result.setOd_stcode("OC");
				result.setOd_quantity(rrList.get(i).getOd_quantity());
				//note오면 note추가

				mod.add(result);
			}
		}else {
			System.out.println("새로운 주문번호 없음");
		}
		if(dao.insNewOd(mod)) {
			insert = true;
		}
		return insert;

	}

	//5. OS테이블 : OC인 애들을 위한 새로운 주문코드 생성
	private boolean insOrdersOC(RequestOrderBean re) {
		List<MroOrderDetailBean> list;
		boolean insert = false;

		re.setRe_state("OC");
		list = dao.selRequest(re);
		if(list!=null) {
			re.setRe_clcode(list.get(0).getOs_clcode());	
			re.setRe_state("OC");

			if(dao.insNewOrders(re)) {
				insert = true;
			}
		}else {
			System.out.println("새로운 주문번호 없음");
		}

		return insert;

	}

	//4.RD테이블 : RR인 애들을 위한 새로운 주문코드 생성
	private boolean insNewRdRA(RequestOrderBean re) {
		List<MroOrderDetailBean> mod = new ArrayList<MroOrderDetailBean>();
		List<MroOrderDetailBean> rrList;
		boolean insert = false;

		re.setRe_state("PD");
		rrList = dao.selRequest(re);
		System.out.println(rrList);//RR인 애들만 뽑아옴.
		if(rrList!=null) {
			for(int i=0; i<rrList.size(); i++) {		
				//새로운 주문코드를 만든다. 그 주문코드의 oc의 정보들을 넣기 od,os인설트
				MroOrderDetailBean result  = new MroOrderDetailBean();
				result.setOd_oscode(dao.checkCount());
				result.setOd_prcode(rrList.get(i).getOd_prcode());
				result.setOd_prspcode(rrList.get(i).getOd_prspcode());
				result.setOd_stcode("RA");
				result.setOd_quantity(rrList.get(i).getOd_quantity());
				//note오면 note추가

				mod.add(result);
			}
		}else {
			System.out.println("새로운 주문번호 없음");
		}
		if(dao.insNewRd(mod)) {
			insert = true;
		}
		return insert;
	}


	//3. RE테이블 : RR인애들을 위한 새로운 주문코드 생성
	private boolean insNewRequestRA(RequestOrderBean re) {
		List<MroOrderDetailBean> list;
		boolean insert = false;

		re.setRe_state("PD");
		list = dao.selRequest(re);
		if(list!=null) {
			re.setRe_oscode(dao.checkCount());
			re.setRe_clcode(list.get(0).getOs_clcode());	
			re.setRe_state("RA");
			re.setRe_spcode(list.get(0).getOd_prspcode());

			if(dao.insNewRequest(re)) {
				insert = true;
			}
		}else {
			System.out.println("새로운 주문번호 없음");
		}

		return insert;
	}



	//2. od 테이블 : RR인애들을 위한 새로운 주문코드 생성
	private boolean insNewOdRA(RequestOrderBean re) {
		List<MroOrderDetailBean> mod = new ArrayList<MroOrderDetailBean>();
		List<MroOrderDetailBean> rrList;
		boolean insert = false;

		re.setRe_state("PD");
		rrList = dao.selRequest(re);
		System.out.println(rrList);//RR인 애들만 뽑아옴.
		if(rrList!=null) {
			for(int i=0; i<rrList.size(); i++) {		
				//새로운 주문코드를 만든다. 그 주문코드의 oc의 정보들을 넣기 od,os인설트
				MroOrderDetailBean result  = new MroOrderDetailBean();
				result.setOd_oscode(dao.checkCount());
				result.setOd_prcode(rrList.get(i).getOd_prcode());
				result.setOd_prspcode(rrList.get(i).getOd_prspcode());
				result.setOd_stcode("RA");
				result.setOd_quantity(rrList.get(i).getOd_quantity());
				//note오면 note추가

				mod.add(result);
			}
		}else {
			System.out.println("새로운 주문번호 없음");
		}
		if(dao.insNewOd(mod)) {
			insert = true;
		}
		return insert;

	}

	//1. os 테이블 : RR인 애들을 위한 새로운 주문코드 생성
	private boolean insNewOrdersRA(RequestOrderBean re) {
		List<MroOrderDetailBean> list;
		boolean insert = false;

		re.setRe_state("PD");
		list = dao.selRequest(re);
		if(list!=null) {		
			re.setRe_clcode(list.get(0).getOs_clcode());	
			re.setRe_state("RA");

			if(dao.insNewOrders(re)) {
				insert = true;
			}
		}else {
			System.out.println("새로운 주문번호 없음");
		}

		return insert;
	}

	//교환 요청에 대한 응답 
	String supplyResponseExchange(RequestOrderBean re) {
		String message="";
		DeliveryBean db = new DeliveryBean();
		db.setDl_oscode(re.getRe_code());
		db.setDl_dvcode("IYJ032");
		db.setDl_dscode("1");


		//교환요청에 수락이면
		if(re.getRe_state().equals("EA")) {
			//OD 테이블 :  오리지널 주문코드 ER-> EC(폐기)
			if(dao.supplyResponseExchangeOD(re)) {
				if(dao.supplyResponseExchangeOS(re)) {
					if(dao.supplyResponseExchageRD(re)) {
						if(dao.supplyResponseExchageRE(re)) {
							if(dao.makeDeliveryLocate()) {
								db.setDl_lccode(dao.maxLCcode());//마지막 운송장번호를 가져와서 lcCode에넣는다.								
								if(dao.supplyAskDelivery(db)) { //운송장번호 발급
									message="교환요청이 정상적으로 처리되었습니다.";
									System.out.println("교환수락");
								}
							}
						}
					}
				}
			}
		}else {//교환요청이 거절이면 (EE)
			re.setRe_state("OC");//거절 누르면 OC로 들어감
			if(dao.supplyResponseExchangeOD(re)) {
				if(dao.supplyResponseExchangeOS(re)) {
					if(dao.supplyResponseExchageRD(re)) {
						if(dao.supplyResponseExchageRE(re)) {
							message="교환요청이 거절 처리되었습니다.";
							System.out.println("교환거절");
						}
					}
				}
			}

		}


		return message;
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
	
}
