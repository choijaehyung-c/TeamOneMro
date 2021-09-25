package mrone.client.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;

import mrone.mro.service.MroServiceEntrance;
import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.ClientOrderBean;
import mrone.teamone.beans.ClientOrderDecide;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.beans.RequestOrderDetailBean;
import mrone.teamone.beans.TaxBean;
import mrone.teamone.utill.Encryption;
import mrone.teamone.utill.ProjectUtils;

@Service
class ClientServiceCtl {
	@Autowired
	ClientDaoCJH dao;
	@Autowired
	ProjectUtils pu;
	@Autowired
	Encryption enc;
	@Autowired
	MroServiceEntrance msec;
	
	
	String updOrderDecide(ClientOrderDecide cd) {
		boolean tran = false;
		pu.setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,
				TransactionDefinition.ISOLATION_READ_COMMITTED, false);
		
		String os_code = cd.getOs_code();
		
		try {//cd.setClpwd(enc.aesEncode(cd.getClpwd(),cd.getClcode()));
			cd.setClpwd("KwpMuMx0nO6jCjpD//ucgA==");//일단 강제 입력 수정해야함
		} catch (Exception e) {System.out.println("error csc");}
		
		if (dao.isClient(cd.getClcode())) {
			if (dao.isClientPwd(cd.getClpwd())) {
				String re_code = dao.getRecodeOs(os_code);
				if (dao.updOrderDecide(os_code)) {
					if (dao.updOrderDetailDecide(os_code)) {
						if (dao.updRequestDecide(os_code)) {
							if (dao.updRequestDetailDecide(re_code)) {
								tran = true;
							}
						}
					}
				}		
			}
		}
		
		pu.setTransactionResult(tran);
		return tran?"success":"failed";
	}
	
	
	List<String> clientRequestCtl(ClientOrderBean co,String type){
		Set<String> sp = new HashSet<>();
		co.setOs_state(type);
		//받아온 비밀번호 복호화해서 셋
		try {//co.setCl_pwd(enc.aesEncode(co.getCl_pwd(),co.getOs_clcode()));
			co.setCl_pwd("KwpMuMx0nO6jCjpD//ucgA==");//일단 강제 입력 수정해야함
		} catch (Exception e) {System.out.println("error csc");}
		
		List<String> oscodes = new ArrayList<String>();
		if (dao.isClient(co.getOs_clcode())) {
			if (dao.isClientPwd(co.getCl_pwd())) {
				
				for (int i = 0; i < co.getOd().size(); i++) { 
					sp.add(co.getOd().get(i).getOd_prspcode());
					co.getOd().get(i).setOd_stcode(type);
				}
				
				for(String sp_code : sp) {
					if ((this.clientOrderProcess(co,sp_code))!=null) {
						oscodes.add(co.getOs_code());
						this.clientRequestProcess(co,sp_code);}
				}
			}
		}
		System.out.println(oscodes);
		return oscodes;
	}
	
	String supplyRequestCtl(ClientOrderBean co, String type) {
		co.setOs_state(type);
		String oscode = null;
		if (this.clientOrderProcess(co, co.getSp_code()) != null) {
			oscode= co.getOd().get(0).getOd_oscode();
			this.clientRequestProcess(co, co.getSp_code());
		}
		return oscode;
	}
	
	String clientOrderProcess(ClientOrderBean co, String sp_code) {
		boolean tran = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal = Calendar.getInstance();
		co.setOs_date(sdf.format(cal.getTime()));
		System.out.println(co.getOs_date());
		
		if(co.getOs_origin()==null)co.setOs_origin("");
		if (dao.insClientOrder(co)) {
			co.setOs_code(dao.getOrderData(co));
			int tranCount = 0;
			for (int i = 0; i < co.getOd().size(); i++) {
				if (co.getOd().get(i).getOd_prspcode().equals(sp_code)) {
					co.getOd().get(i).setOd_oscode(co.getOs_code());
					if(co.getOd().get(i).getOd_note()==null)co.getOd().get(i).setOd_note("");
					if (!dao.insClientOrderDetail(co.getOd().get(i))) {
						break;
					}
				}
				tranCount++;
			}
			System.out.println(tranCount + ":" + co.getOd().size());
			if (tranCount == co.getOd().size())tran = true;
			else co.setOs_code(null);

		}
		return co.getOs_code();
	}
	
	boolean clientRequestProcess(ClientOrderBean co,String sp_code) {
		boolean tf = false;
		RequestOrderBean ro = new RequestOrderBean();
		List<RequestOrderDetailBean> list = new ArrayList<RequestOrderDetailBean>();
		ro.setRe_clcode(co.getOs_clcode());
		System.out.println(co.getOs_code());
		ro.setRe_oscode(co.getOs_code());
		ro.setRe_state(co.getOs_state());
		ro.setRe_spcode(sp_code);
		if (co.getOs_origin() != null) {
			ro.setRe_origin(co.getOs_origin());
		}
		for (int i = 0; i < co.getOd().size(); i++) {
			if(co.getOd().get(i).getOd_prspcode().equals(sp_code)){
				RequestOrderDetailBean rd = new RequestOrderDetailBean();
				rd.setRd_prspcode(co.getOd().get(i).getOd_prspcode());
				rd.setRd_prcode(co.getOd().get(i).getOd_prcode());
				rd.setRd_quantity(co.getOd().get(i).getOd_quantity());
				rd.setRd_stcode(co.getOd().get(i).getOd_stcode());
				if (co.getOd().get(i).getOd_note() != null) {
					rd.setRd_note(co.getOd().get(i).getOd_note());
				}
				list.add(rd);
			}
		}
		ro.setRd(list);
		
		if (co.getOs_state().equals("OR")? msec.mroRequestOrder(ro):co.getOs_state().equals("OA")?msec.mroRequestReOrder(ro)
				: co.getOs_state().equals("RR") ? msec.mroRequestRefund(ro) : msec.mroRequestExchange(ro)) {
			tf = true;
		}
		return tf;
	}
	
	


	public List<TaxBean> clientGetTaxbillListCtl(ClientInfoBean ci) throws Exception {
		List<TaxBean> list = null;
		if(dao.isClient(ci.getCl_code())) {
			if(dao.isClientPwd(ci.getCl_pwd())) {
				list = dao.getTaxBillList(ci);
				
				for(int i = 0 ; i < list.size(); i++) {
					list.get(i).setTb_ttprice(enc.aesDecode(list.get(i).getTb_ttprice(), ci.getCl_code()) );
				}
			}
		}
		
		
		return list;
	}
	
	public TaxBean clientGetTaxbillDetailCtl(ClientInfoBean ci) throws Exception {
		TaxBean data = null;
		if(dao.isClient(ci.getCl_code())) {
			if(dao.isClientPwd(ci.getCl_pwd())) {
				data = dao.getTaxBillDetail(ci);
				data.setTb_ttprice(enc.aesDecode(data.getTb_ttprice(), ci.getCl_code()) );
				data.setTb_price(enc.aesDecode(data.getTb_price(), ci.getCl_code()) );
				data.setTb_tax(enc.aesDecode(data.getTb_tax(), ci.getCl_code()) );
			}
		}
		return data;
	}
}