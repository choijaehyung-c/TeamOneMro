package mrone.supply.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;

import mrone.client.service.ClientServiceEntrance;
import mrone.mro.service.MroServiceEntrance;
import mrone.teamone.beans.CGBean;
import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.ClientOrderBean;
import mrone.teamone.beans.DeliveryBean;
import mrone.teamone.beans.DeliveryInsert;
import mrone.teamone.beans.DriverLocationBean;
import mrone.teamone.beans.OrderDetailBean;
import mrone.teamone.beans.ProductBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;
import mrone.teamone.beans.SupplyInfoBean;
import mrone.teamone.beans.SupplyResponse;
import mrone.teamone.beans.SupplySearchBean;
import mrone.teamone.beans.TaxBean;
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
	@Autowired
	ClientServiceEntrance cse;
	@Autowired
	MroServiceEntrance mse;
	/* test */
		List<DeliveryBean> deliveryTest() {
		DeliveryBean db = new DeliveryBean();
		try {
			db.setDl_dvcode((String)pu.getAttribute("dvcode"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	      return dao.deliveryTest(db);
	   }

	   public void insertsdcode(DeliveryBean db) {
	      dao.insertsdcode(db);
	   }
		/* test */
	
	
	
	
	   //수정1
	   List<RequestOrderBean> waitOrderlist() {

	      List<RequestOrderBean> reList = null;
	      String spcode = null;
	      try {
	         if(pu.getAttribute("userSs") != null) {
	            spcode=enc.aesDecode((String)pu.getAttribute("type"),enc.aesDecode((String)pu.getAttribute("userSs"),"session"));              
	         }
	      } catch (Exception e) {

	         e.printStackTrace();
	      }

	      reList = dao.waitOrderlist(spcode);

	      for(int i =0; i<reList.size(); i++) {
	         if(reList.get(i).getRe_state().equals("OR")) {
	            reList.get(i).setRe_state("주문요청");
	         }
	      }
	      return reList;
	   }

	   //수정1
	   List<RequestOrderBean> clearOrderlist() {
	      
	      String spcode = null;
	      try {
	         if(pu.getAttribute("userSs") != null) {
	            spcode=enc.aesDecode((String)pu.getAttribute("type"),enc.aesDecode((String)pu.getAttribute("userSs"),"session"));              
	         }
	      } catch (Exception e) {

	         e.printStackTrace();
	      }

	      List<RequestOrderBean> reList = null;
	      reList = dao.clearOrderlist(spcode);
	      
	      for(int i =0; i<reList.size(); i++) {
	         if(reList.get(i).getRe_state().equals("OA")) {
	            reList.get(i).setRe_state("주문수락");
	         }
	      }
	   
	      
	      return reList;
	   }

	   List<RequestOrderDetailBean> waitOrderlistD(String recode) {

	      List<RequestOrderDetailBean> reList = null;
	      reList = dao.waitOrderlistD(recode);

	      for(int i=0; i<reList.size(); i++) {
	         if(reList.get(i).getRd_stcode().equals("OR")) {
	            reList.get(i).setRd_stcode("주문요청");
	         }
	            
	      }
	      return reList;
	   }
	   
	   List<RequestOrderDetailBean> clearOrderlistD(String recode) {

	      List<RequestOrderDetailBean> reList = null;
	      reList = dao.clearOrderlistD(recode);

	      for(int i=0; i<reList.size(); i++) {
	         if(reList.get(i).getRd_stcode().equals("OA")) {
	            reList.get(i).setRd_stcode("주문수락");
	         }
	            
	      }
	      return reList;
	   }
	   
	   //수정1
	   List<RequestOrderBean> getSupplyRefuseOrderList() {
	      String spcode = null;
	      try {
	         if(pu.getAttribute("userSs") != null) {
	            spcode=enc.aesDecode((String)pu.getAttribute("type"),enc.aesDecode((String)pu.getAttribute("userSs"),"session"));              
	         }
	      } catch (Exception e) {

	         e.printStackTrace();
	      }

	   List<RequestOrderBean> reList = null;
	      reList = dao.RefuseOrderlist(spcode);
	      
	      for(int i =0; i<reList.size(); i++) {
	         if(reList.get(i).getRe_state().equals("OF")) {
	            reList.get(i).setRe_state("주문거절");
	         }
	      } 
	      return reList;
	   }
	   
	   //수정1
	   List<RequestOrderDetailBean> getSupplyRefuseOrderListDetail(String recode) {
	      List<RequestOrderDetailBean> reList = null;
	      reList = dao.refuseOrderListD(recode);

	      for(int i=0; i<reList.size(); i++) {
	         if(reList.get(i).getRd_stcode().equals("OF")) {
	            reList.get(i).setRd_stcode("주문거절");
	         }
	            
	      }
	      return reList;
	   }
	   
	   //수정1
	   String supplyGoDelivery(String recode) {
	      String message;
	      
	      if(dao.supplyGoDelivery(recode)) {
	         message="상품이 고객사로 출발하였습니다.";
	      }else {
	         message="다시 시도해주세요.";
	      }
	      
	      return message;
	   }
	   
	   //수정1
	   List<DeliveryBean> getTrackDeliveryList() {
	      String spcode = null;
	      try {
	         if(pu.getAttribute("userSs") != null) {
	            spcode=enc.aesDecode((String)pu.getAttribute("type"),enc.aesDecode((String)pu.getAttribute("userSs"),"session"));              
	         }
	      } catch (Exception e) {

	         e.printStackTrace();
	      }
	         //re, rd, os, od도 state를 배송으로 바꿔야...  cause-주문수락상태가 아닌데, 주문수락목록에 뜬다.

	      return dao.getTrackDeliveryList(spcode);
	   }
	   
//	   //수정1
//	   List<DeliveryBean> getTrackDL(String recode) {
//	      List<DeliveryBean> list;
//	      list = dao.getTrackDL(recode);
//	      
//	      for(int i=0; i<list.size(); i++) {
//	         if(list.get(i).getLc_x().equals("0") && list.get(i).getLc_y().equals("0")) {
//	            list.get(i).setLc_x("출고지");
//	         }
//	      }
//	      
//	      return list;
//	   }
	
	List<ProductBean> supplyAllProductList(){
	      String spcode = null;
	      try {
	         spcode = enc.aesDecode((String)pu.getAttribute("type"),enc.aesDecode((String)pu.getAttribute("userSs"),"session"));
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return dao.supplyAllProductList(spcode);
	   }
	
	String supplyRequestCancel(ProductBean pb) {
		String message = null;
		if(dao.supplyRequestCancel(pb)) {
			message = "success";
		} else {
			message = "fail";
		}
		return message;
	}
	
	List<ProductBean> supplyPRAFProductList() {
		String spcode = null;
		try {
			spcode = enc.aesDecode((String)pu.getAttribute("type"),enc.aesDecode((String)pu.getAttribute("userSs"),"session"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.supplyPRAFProductList(spcode);
	}
	
	List<ProductBean> supplyMRDRDAProductList() {
		String spcode = null;
		try {
			spcode = enc.aesDecode((String)pu.getAttribute("type"),enc.aesDecode((String)pu.getAttribute("userSs"),"session"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.supplyMRDRDAProductList(spcode);
	}
	
	
	List<RequestOrderBean> getSupplyDealListCtl() {
		String spcode = null;
		try {
			if(pu.getAttribute("userSs") != null) {
				spcode=enc.aesDecode((String)pu.getAttribute("type"),enc.aesDecode((String)pu.getAttribute("userSs"),"session"));
			}
		} catch (Exception e) {
						
			e.printStackTrace();
		}
		
		return dao.getSupplyDealList(spcode);
		
	}

	List<RequestOrderDetailBean> getSupplyDealDetailCtl(String re_code) {
		int sum=0;
		List<RequestOrderDetailBean> list = null;		
		list = dao.getSupplyDealDetail(re_code);
		for(int i=0; i<list.size(); i++) {
			sum += list.get(i).getRd_quantity()*(list.get(i).getPr_price()+list.get(i).getPr_tax());
		}		
		list.get(list.size()-1).setPr_ttprice(sum);
		
		return list;
	}

	List<SupplySearchBean> getSearchSupplyDealCtl(String word) {
		return dao.getSearchSupplyDeal(word);
	}

	String issueTaxCtl(TaxBean tb) {	
		String message = null;		
		for(int i=0; i<tb.getRdb().size(); i++) {
		tb.setTb_price(Integer.toString(tb.getRdb().get(i).getPr_price()));
		tb.setTb_tax(Integer.toString(tb.getRdb().get(i).getPr_tax()));		
		tb.setTb_oscode(tb.getRdb().get(i).getRd_recode());
		}
		tb.setTb_spcode(tb.getSb().getSp_code()); 
		tb.setTb_spname(tb.getSb().getSp_name());
		tb.setTb_spaddress(tb.getSb().getSp_address()); 
		tb.setTb_spbtype(tb.getSb().getSp_btype());
		tb.setTb_spcorpnum(tb.getSb().getSp_corpnum()); 
		tb.setTb_spbkind(tb.getSb().getSp_bkind()); 		
		tb.setTb_clcode(tb.getCb().getCl_code());
		tb.setTb_clcorpnum(tb.getCb().getCl_corpnum());
		tb.setTb_clname(tb.getCb().getCl_name());
		tb.setTb_claddress(tb.getCb().getCl_address());
		tb.setTb_clbtype(tb.getCb().getCl_btype());
		tb.setTb_clbkind(tb.getCb().getCl_bkind());
		tb.setTb_ttprice(tb.getTb_ttprice());
		tb.setTb_spemail(tb.getSb().getSd_email()); 
		tb.setTb_clemail(tb.getCb().getCl_email()); 
		System.out.println(tb);
		if(dao.issueTax(tb)) {
			message ="insert";
		}else {
			
		}
		return message;
	}
	
	List<TaxBean> getIssuedTaxCtl() {	
		String spcode = null;
		try {
			if(pu.getAttribute("userSs") != null) {
				spcode=enc.aesDecode((String)pu.getAttribute("type"),enc.aesDecode((String)pu.getAttribute("userSs"),"session"));
				
			}
		} catch (Exception e) {
						
			e.printStackTrace();
		}
		return dao.getIssuedTax(spcode);
	}
	
	TaxBean getIssuedTaxDetailCtl(String tbcode) {
		TaxBean tb = dao.getIssuedTaxDetail(tbcode);
		tb.setOd(dao.getTaxProduct(tb.getTb_oscode()));
		return tb;
	}
	
	List<ProductBean> getSupplyCateProductList(ProductBean pb) {
		return dao.getSupplyCateProductList(pb);
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


	ClientInfoBean choiceCLInfo(String cl_code) {
		return dao.choiceCLInfo(cl_code);
	}

	SupplyInfoBean choiceSPInfo() {
		String spcode = null;
		try {
			if(pu.getAttribute("userSs") != null) {
				spcode=enc.aesDecode((String)pu.getAttribute("type"),enc.aesDecode((String)pu.getAttribute("userSs"),"session"));
			}
		} catch (Exception e) {
						
			e.printStackTrace();
		}
		return dao.choiceSPInfo(spcode);
	}


	List<RequestOrderBean> getTaxdeal() {
		String spcode = null;
		try {
			if(pu.getAttribute("userSs") != null) {
				spcode=enc.aesDecode((String)pu.getAttribute("type"),enc.aesDecode((String)pu.getAttribute("userSs"),"session"));
				
			}
		} catch (Exception e) {
						
			e.printStackTrace();
		}
		return dao.getTaxdeal(spcode);
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
		String message = "";
		// 해당 상품코드의 PR_STCODE에 MR인 애가 있는지 확인
		if (dao.MRCheck(pb)) {
				dao.deleteDR(pb);
			if (dao.updateSupplyRequestModify(pb)) {
				message = "success";
			} else {
				message = "fail";
			}

		} else {
				dao.deleteDR(pb);
			if (dao.supplyRequestModify(pb)) {
				message = "success";
			} else {
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
		String message = "";
		if (dao.MRCheck(pb)) {
			//삭제 신청시 변겨요청이 있으면 헤당 컬럼 삭제하고 삭제요청함
				dao.deleteMR(pb);
			// 이미 PR_STCODE에 DR이 있을경우 그냥 업데이트
			if (dao.updateSupplyRequestModify(pb)) {
				message = "success";
			} else {
				message = "fail";
			}

		} else {
				dao.deleteMR(pb);
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

	String supplyRequestNewProduct(ProductBean pb,HttpServletRequest req) {
		boolean tran = false;
		pu.setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,
				TransactionDefinition.ISOLATION_READ_COMMITTED, false);
		pb.setPr_image("http://cleverc.online/vue/getImage/"+pu.setFile(pb.getFile(),req));
		try {
		pb.setPr_spcode(
			enc.aesDecode((String)pu.getAttribute("type"),enc.aesDecode((String)pu.getAttribute("userSs"),"session"))
			);

		} catch (Exception e) {
			pb.setPr_spcode("");
		}
		pb.setPr_tax(String.valueOf(Math.round(Integer.parseInt(pb.getPr_price()) / 10)));
		pb.setPr_spbkind(pb.getBk_code());
		pb.setPr_stcode("PR");
		if (dao.supplyRequestNewProduct(pb)) {
			tran = true;
		} else {
			tran = false;
		}
		pu.setTransactionResult(tran);
		return tran?"seccess":"failed";
	}
//------------------------------------------------------------
	//수정완료 re에 st가 RR인 주문서 리턴 
	List<RequestOrderBean> supplyReceiveRefundListForm() {
		RequestOrderBean ro= new RequestOrderBean();
		try {
			ro.setRe_spcode(enc.aesDecode((String)pu.getAttribute("type"),enc.aesDecode((String)pu.getAttribute("userSs"),"session")));
			ro.setRe_state(" 'RR' OR RE.RE_STATE = 'RA' OR RE.RE_STATE = 'FF'");
		} catch (Exception e) {e.printStackTrace();}
		List<RequestOrderBean> roList = dao.getReceiveAsListSp(ro);
		for(int i=0;i<roList.size();i++) {
			List<String> rd = dao.getPrnameAndCount(roList.get(i));
			if(!rd.isEmpty()) {
				if(rd.size()>1) {
					roList.get(i).setWord(rd.get(0)+" 외 "+(rd.size()-1)+"건");
				}else {
					roList.get(i).setWord(rd.get(0)+rd.size()+" 1건");
				}
			}
		}
		return roList;
	}
	//수정완료 re에 st가 ER인 주문서 리턴
	List<RequestOrderBean> supplyReceiveExchangeListForm() {
		RequestOrderBean ro= new RequestOrderBean();
		try {
			ro.setRe_spcode(enc.aesDecode((String)pu.getAttribute("type"),enc.aesDecode((String)pu.getAttribute("userSs"),"session")));
			ro.setRe_state(" 'RR' OR RE.RE_STATE = 'EA' OR RE.RE_STATE = 'EF'");
		} catch (Exception e) {e.printStackTrace();}
		List<RequestOrderBean> roList = dao.getReceiveAsListSp(ro);
		for(int i=0;i<roList.size();i++) {
			List<String> rd = dao.getPrnameAndCount(roList.get(i));
			if(rd != null) {
				if(rd.size()>1) {
					roList.get(i).setWord(rd.get(0)+" 외 "+(rd.size()-1)+"건");
				}else {
					roList.get(i).setWord(rd.get(0)+rd.size()+" 1건");
				}
			}
		}
		
		
		return roList;
	}

	//수정완료 반품 또는 교환 디테일
	List<RequestOrderDetailBean> supplyReceiveAsDetail(String re_code,String type){
		RequestOrderBean ro = new RequestOrderBean();
		ro.setRe_state(type);
		ro.setRe_code(re_code);
		List<RequestOrderDetailBean> list = dao.supplyReceiveAsDetail(ro);
		for(int i=0;i<list.size();i++) {list.get(i).setPr_ttprice(list.get(i).getPr_price()+list.get(i).getPr_tax());}
		return list;
	}

	//공급사 - 반품에 대한 응답(거절(FF)or수락(RA))
	String supplyResponseRefund(RequestOrderBean ro) {
		boolean tran = false;
		pu.setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,
				TransactionDefinition.ISOLATION_READ_COMMITTED, false);
		//ro에 re_code, re_state에 수락 or 거절 코드 담아서옴, ro-rd에 거절사유+
		//프론트에서 반품,교환 동시 진행 X (오리진코드로 이미 진행중인 반품,교환이 있으면 중복 접수X)
		SupplyResponse sr = new SupplyResponse();
		sr.setAfter(ro.getRe_state());
		sr.setBefore("RR");
		sr.setRe_code(ro.getRe_code());
		sr.setOs_code(dao.getInvolvedOscode(sr));
		//수락,거절 공통 업데이트
		if(this.updateResponseProcess(sr)) {
			if (sr.getAfter().equals("FF")) {// 거절
				tran = updateReasonProcess(ro,sr);
			} else if (sr.getAfter().equals("RA")) {// 수락
				// 반품안한거 새주문
				String newOscode;
				ClientOrderBean newCo = new ClientOrderBean();
				newCo.setOd(dao.getNewODForRefund(sr.getOs_code()));
				if(!newCo.getOd().isEmpty()) {
				String clcode = dao.getCLForRefund(sr.getRe_code());
				newCo.setOs_clcode(clcode);
				newCo.setOs_origin(sr.getOs_code());
				newCo.setOs_region(dao.getRegion(sr.getOs_code()));
				String spcode = null;
				try {
					if(pu.getAttribute("userSs") != null) {
						spcode=enc.aesDecode((String)pu.getAttribute("type"),enc.aesDecode((String)pu.getAttribute("userSs"),"session"));		        
					}
				} catch (Exception e) {

					e.printStackTrace();
				}
				newCo.setSp_code(spcode);
				newOscode = cse.supplyRequestOrder(newCo);
				}else {
				newOscode= "good";	
				}
				if (newOscode!=null) {
						// 오리진 주문,발주서 폐기처리//오리진코드 받아와
						sr.setRe_code(dao.getREOriginCode(ro.getRe_code()));
						sr.setOs_code(dao.getOSOriginCode(sr.getOs_code()));
						sr.setAfter("PD");
						sr.setBefore("OA");
						if(this.updateResponseProcess(sr)) {
							tran = true;
						}
				}
				
			}
		}
		pu.setTransactionResult(tran);
		return tran?"success":"failed";
	}
	
	boolean updateResponseProcess(SupplyResponse sr) {
		boolean tran = false;
		if (dao.updRequest(sr)) {
			if (dao.updRequestDetail(sr)) {
				if (dao.updOrder(sr)) {
					if (dao.updOrderDetail(sr)) {
						tran = true;
					}
				}
			}
		}
		return tran;
	}

	boolean updateReasonProcess(RequestOrderBean ro,SupplyResponse sr) {
		int count = 0;
		for (int i = 0; i < ro.getRd().size(); i++) {
			ro.getRd().get(i).setRd_stcode(sr.getAfter());
			ro.getRd().get(i).setRd_recode(sr.getRe_code());
			if (ro.getRd().get(i).getRd_note() != null) {
				
				if (dao.updReasonRD(ro.getRd().get(i))) {
					
					ro.getRd().get(i).setRd_recode(sr.getOs_code());
					if (dao.updReasonOD(ro.getRd().get(i))) {
						count++;
					}
				}
			}
		}
		return (ro.getRd().size()==count)?true:false;
	}

	String supplyResponseCtl(RequestOrderBean ro) {
		boolean tran = false;
		pu.setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,
				TransactionDefinition.ISOLATION_READ_COMMITTED, false);
		//decision == OA EA OF EF 요청 응답 st 코드
		String decision = ro.getRe_state();
		SupplyResponse sr = new SupplyResponse();
		if(decision.equals("OA") || decision.equals("OF")) {
			sr.setAfter(decision);
			sr.setBefore("OR");
			sr.setRe_code(ro.getRe_code());
			sr.setOs_code(dao.getInvolvedOscode(sr));
			if(this.updateResponseProcess(sr)) {
				if(decision.equals("OF")) {
					tran = this.updateReasonProcess(ro,sr);
				}else {
					tran = this.issueDelivery(sr.getOs_code());
				}
			}
			//수락일경우 -> 운송장 거절일경우 -> 거절 사유업데이트
		}else if(decision.equals("EA") || decision.equals("EF")){
			sr.setAfter(decision);
			sr.setBefore("ER");
			sr.setRe_code(ro.getRe_code());
			sr.setOs_code(dao.getInvolvedOscode(sr));
			if(this.updateResponseProcess(sr)) {
				if(decision.equals("EF")) {
					tran = this.updateReasonProcess(ro,sr);
				}else {
					tran = this.issueDelivery(sr.getOs_code());
				}
			}
		}
		pu.setTransactionResult(tran);
		return tran?"success":"failed";
	}
	
	boolean issueDelivery(String oscode) {
		boolean tran = false;
		DeliveryInsert di = new DeliveryInsert();
		di.setOs_code(oscode);
		di.setDv_code(dao.getDriver());
		if(dao.insertFirstLC(oscode)) {
			di.setLc_code(dao.getRecentlyLC(oscode));
			if(dao.insertFirstDL(di))tran = true;
		}
		return tran;
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
	
	List<ProductBean> supplyGetBK() {
		return dao.supplyGetBK();
	}

	List<ProductBean> supplyProductList(ProductBean pd) {
		try {
			pd.setPr_spcode(enc.aesDecode((String)pu.getAttribute("type"),enc.aesDecode((String)pu.getAttribute("userSs"),"session")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			pd.setPr_spcode("");
		}
		return dao.supplyProductList(pd);
	}

	List<ProductBean> supplySearchProduct(ProductBean pd) {
		try {
			pd.setPr_spcode(enc.aesDecode((String)pu.getAttribute("type"),enc.aesDecode((String)pu.getAttribute("userSs"),"session")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			pd.setPr_spcode("");
			e.printStackTrace();
		}
		return dao.supplySearchProduct(pd);
	}
	//수정2
	List<RequestOrderDetailBean> getChart() {
		String spcode = null;
		try {
			spcode = enc.aesDecode((String)pu.getAttribute("type"),enc.aesDecode((String)pu.getAttribute("userSs"),"session"));
		} catch (Exception e) {
			e.printStackTrace();
		}
			
			List<RequestOrderDetailBean> list = dao.getChart(spcode);
			int lcount = list.size();
			if(lcount < 5) {
				for(int i=0 ; i < 5-lcount ; i++) {
					RequestOrderDetailBean ob = new RequestOrderDetailBean();
					ob.setPr_name("구매내역없음");
					ob.setRd_quantity(0);
					list.add(ob);
				}
			}
			
			
		return list;
	}

	public String insertGPS(DriverLocationBean dlb) {
		String msg = null;
		String dvcode = null;
		try {
			dvcode = (String)pu.getAttribute("dvcode");
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<DeliveryBean> sb = dao.seleteDL(dvcode);
		int sbsize = sb.size();
		int count = 0;
		boolean tran = false;
		pu.setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,
				TransactionDefinition.ISOLATION_READ_COMMITTED, false);
		for(int i=0; i<sbsize; i++) {
			sb.get(i).setLc_x(dlb.getLc_x());
			sb.get(i).setLc_y(dlb.getLc_y());
			if(dao.insertXY(sb.get(i))) {
				count++;
			}
		}
		if(count == sbsize) {
			tran = true;
			msg="현재위치 갱신성공";
		}else {
			tran= false;
			msg="현재위치 갱신실패";
		}
		pu.setTransactionResult(tran);
		return msg;
	}

	public List<CGBean> supplyGetSellProductCate() {
		String spcode =null;
		try {
			spcode=enc.aesDecode((String)pu.getAttribute("type"),enc.aesDecode((String)pu.getAttribute("userSs"),"session"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.supplyGetSellProductCate(spcode);
	}
	
}
