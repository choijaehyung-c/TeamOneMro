package mrone.mro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.DeliveryBean;
import mrone.teamone.beans.MroOrderBean;
import mrone.teamone.beans.MroOrderDetailBean;
import mrone.teamone.beans.ProductBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.SupplyInfoBean;

@Service
public class MroServiceEntrance {

	@Autowired
	MroServiceCtl msc;
	
	// 공급사리스트
	public List<SupplyInfoBean> SupplyListCtl() {
		return msc.SupplyList();
	}

	// 고객사리스트
	public List<ClientInfoBean> ClientListCtl() {
		return msc.ClientList();
	}

	// mro 주문대기리스트
	public List<MroOrderBean> getWaitOrderListCtl() {
		
		return msc.getWaitOrderList();
	}

	// 주문대기,반품요청, 교환요청 상세보기
	public List<MroOrderDetailBean> getOrderDetail(String osCode) {
		return msc.getOrderDetail(osCode);
	}

	// 반품요청 리스트
	public List<MroOrderBean> getRefundListCtl() {
		return msc.getRefundList();
	}

	// 교환요청 리스트
	public List<MroOrderBean> getExchangeListCtl() {
		return msc.getExchangeList();
	}
	
	public String mroRequestOrder(RequestOrderBean ro) {
		return msc.mroRequestCtl(ro, "OR");
	}

	public String mroRequestRefund(RequestOrderBean ro) {
		return msc.mroRequestCtl(ro, "RR");
	}

	public String mroRequestExchange(RequestOrderBean ro) {
		return msc.mroRequestCtl(ro, "ER");
	}

	public List<ProductBean> getRequestRegisterNewProductList() {
		return msc.getRequestRegisterNewProductList();
	}

	public String mroResponseModifyProduct(ProductBean pb) {
		return msc.mroResponseModifyProduct(pb);
	}

	public ProductBean mroGetModifyProductDetail(ProductBean pb) {
		return msc.mroGetModifyProductDetail(pb);
	}

	public List<ProductBean> callModifyRequestList() {
		return msc.callModifyRequestList();
	}

	public String mroResponseNewProduct(ProductBean pb) {
		return msc.mroResponseNewProduct(pb);
	}

	public ProductBean mroGetNewProductDetail(ProductBean pb) {
		return msc.mroGetNewProductDetail(pb);
	}

	
	
	
	
	
	
	public List<DeliveryBean> deliveryTest(DeliveryBean db) {
		
		return msc.deliveryTest(db);
	}

	public void insertsdcode(DeliveryBean db) {
		msc.insertsdcode(db);
	}

}
