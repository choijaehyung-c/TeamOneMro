package mrone.mro.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;

import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.ClientOrderBean;
import mrone.teamone.beans.MroOrderBean;
import mrone.teamone.beans.MroOrderDetailBean;
import mrone.teamone.beans.ProductBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.SupplyInfoBean;
import mrone.teamone.utill.ProjectUtils;

@Service
class MroServiceCtl {
	@Autowired
	MroDao dao;
	@Autowired
	ProjectUtils pu;
	
	String mroRequestCtl(RequestOrderBean ro, String type) {
		ro.setRe_state(type);
		return this.mroRequestProcess(ro);
	}

	String mroRequestProcess(RequestOrderBean ro) {
		String result = "failure";

		boolean tran = false;

		pu.setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,
				TransactionDefinition.ISOLATION_READ_COMMITTED, false);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal = Calendar.getInstance();
		ro.setRe_date(sdf.format(cal.getTime()));

		if (dao.insMroRequestOrder(ro)) {
			System.out.println("in1");
			int tranCount = 0;
			for (int i = 0; i < ro.getRd().size(); i++) {
				ro.getRd().get(i).setRd_recode(dao.getRequestOrderData(ro));
				if (!dao.insMroRequestOrderDetail(ro.getRd().get(i))) {
					break;
				}
				tranCount++;
			}
			System.out.println(tranCount + ":" + ro.getRd().size());
			if (tranCount == ro.getRd().size()) {
				tran = true;
				result = ""; // dao.getOrderData(co);
			}

		}

		pu.setTransactionResult(tran);

		return result;
	}

	List<ProductBean> getRequestRegisterNewProductList() {
		return dao.getRequestRegisterNewProductList();
	}

	ProductBean mroGetNewProductDetail(ProductBean pb) {
		System.out.println(pb.getPr_code() + "mro");
		System.out.println(pb.getPr_stcode() + "mro");

		return dao.mroGetNewProductDetail(pb);
	}

	String mroResponseNewProduct(ProductBean pb) {
		String message = null;
		if (dao.mroResponseNewProduct(pb)) {
			message = "success!";
		} else {
			message = "fail!";
		}
		return message;
	}

	List<ProductBean> callModifyRequestList() {
		return dao.callModifyRequestList();
	}

	ProductBean mroGetModifyProductDetail(ProductBean pb) {
		return dao.mroGetModifyProductDetail(pb);
	}

	String mroResponseModifyProduct(ProductBean pb) {
		String message = null;
		boolean tran = false;
		pu.setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,
				TransactionDefinition.ISOLATION_READ_COMMITTED, false);

		if (pb.getPr_stcode().equals("PC")) {// 수정요청 수락
			if (dao.updatePCToPD(pb)) {// 해당 상품코드 AND PC인 애를 PD로
				if (dao.mroResponseModifyProduct(pb)) {// 해당상품코드 AND MR인 애를 PC로
					tran = true;
				}
			}
		} else if (pb.getPr_stcode().equals("RF")) { // 수정요청 거절
			if (dao.stcodeCheck(pb)) { // 해당 코드and stcode가 RF인 컬럼이 있는지 체크
				if (dao.deleteStcode(pb)) {// 해당코드의 stcode가 RF인 컬럼을 삭제
					if (dao.mroResponseModifyProduct(pb)) {// 해당 상품코드AND MR인 애를 RF로
						tran = true;
					}
				}
			} else {
				if (dao.mroResponseModifyProduct(pb)) {// 해당 상품코드AND MR인 애를 RF로
					tran = true;
				}
			}

		} else if (pb.getPr_stcode().equals("DA")) {// 삭제요청 수락
			if (dao.mroResponseDeleteProduct(pb)) {// 해당 삭제요청 상품코드 AND DR인 애를 DA로
				if (dao.updatePCToPD(pb)) { // 해당 삭제요청 상품코드 AND PC인 애를 PD로
					tran = true;
				}
			}
		} else {// 삭제요청 거절
			if (dao.stcodeCheck(pb)) {// 해당 코드and stcode가 DF인 컬럼이 있는지 체크
				if (dao.deleteStcode(pb)) {// 해당코드의 stcode가 DF인 컬럼을 삭제
					if (dao.mroResponseDeleteProduct(pb)) {// 해당 삭제요청 상품코드 AND DR인 애를 DF로
						tran = true;
					}
				}
			} else {
				dao.mroResponseDeleteProduct(pb);
				tran = true;
			}
		}

		pu.setTransactionResult(tran);

		message = tran ? "success!" : "fail!";

		return message;
	}

	List<SupplyInfoBean> SupplyList() {
		List<SupplyInfoBean> list;
		list = dao.getSupplyList();

		return list;
	}

	List<ClientInfoBean> ClientList() {
		List<ClientInfoBean> list;
		list = dao.getClientList();

		return list;
	}

	List<MroOrderBean> getWaitOrderList() {
		List<MroOrderBean> waitOrderList;
		waitOrderList = dao.getWaitOrderList();

		for (int i = 0; i < waitOrderList.size(); i++) {
			if (waitOrderList.get(i).getOs_state().equals("OR")) {
				waitOrderList.get(i).setOs_state("대기");
			}
		}

		// System.out.println(waitOrderList);
		return waitOrderList;
	}

	List<MroOrderDetailBean> getOrderDetail(String osCode) {
		List<MroOrderDetailBean> od;
		od = dao.getOrderDetail(osCode);

		for (int i = 0; i < od.size(); i++) {
			if (od.get(i).getOd_stcode().equals("OC")) {
				od.get(i).setOd_stcode("구매확정");
			} else if (od.get(i).getOd_stcode().equals("RR")) {
				od.get(i).setOd_stcode("반품요청");
			} else if (od.get(i).getOd_stcode().equals("ER")) {
				od.get(i).setOd_stcode("교환요청");
			}
		}

		return od;
	}

	List<MroOrderBean> getRefundList() {
		List<MroOrderBean> list = dao.getRefundList();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getOs_state().equals("RR")) {
				list.get(i).setOs_state("반품요청");
			}
		}
		return list;
	}

	List<MroOrderBean> getExchangeList() {
		List<MroOrderBean> list = dao.getExchangeList();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getOs_state().equals("ER")) {
				list.get(i).setOs_state("교환요청");
			}
		}
		return list;
	}

}
