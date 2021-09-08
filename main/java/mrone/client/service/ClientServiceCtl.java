package mrone.client.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;

import mrone.mro.service.MroServiceEntrance;
import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.ClientOrderBean;
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
	
	String clientRequestCtl(ClientOrderBean co,String type){
		co.setOs_state(type);
		String result = "failed";
		if(this.clientOrderProcess(co)) {
		RequestOrderBean ro = new RequestOrderBean();
		List<RequestOrderDetailBean> list = new ArrayList<RequestOrderDetailBean>();
		ro.setRe_clcode(co.getOs_clcode());
		ro.setRe_oscode(co.getOs_code());
		ro.setRe_spcode(co.getSp_code());
		ro.setRe_state(co.getOs_state());
		if(co.getOs_origin()!=null) {
			ro.setRe_origin(co.getOs_origin());
		}
			for(int i = 0 ; i<co.getOd().size(); i++) {
				RequestOrderDetailBean rd = new RequestOrderDetailBean();
				 rd.setRd_prspcode(co.getOd().get(i).getOd_prspcode());
				 rd.setRd_prcode(co.getOd().get(i).getOd_prcode());
				 rd.setRd_quantity(co.getOd().get(i).getOd_quantity());
				 rd.setRd_stcode(co.getOd().get(i).getOd_stcode());
				 if(co.getOd().get(i).getOd_note() != null) {
					 rd.setRd_note(co.getOd().get(i).getOd_note());
				 }
				 list.add(rd);
			}
		ro.setRd(list);
		result=type.equals("OR")?msec.mroRequestOrder(ro):type.equals("RR")?msec.mroRequestRefund(ro):msec.mroRequestExchange(ro);
		}
		return result;
	}
	
	boolean clientOrderProcess(ClientOrderBean co) {
		//String result = "failure";
		
		boolean tran = false;
		pu.setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,
				TransactionDefinition.ISOLATION_READ_COMMITTED, false);
		ClientInfoBean ci = new ClientInfoBean();
		ci.setCl_code(co.getOs_clcode());
		try {
			//ci.setCl_pwd(enc.aesEncode(co.getCl_pwd(),co.getOs_clcode()));
			ci.setCl_pwd("KwpMuMx0nO6jCjpD//ucgA==");//일단 강제 입력 수정해야함
		} catch (Exception e) {System.out.println("error csc");}

		if (dao.isClient(ci)) {
			if (dao.isClientPwd(ci)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				Calendar cal = Calendar.getInstance();
				co.setOs_date(sdf.format(cal.getTime()));
				System.out.println( co.getOs_date());
				if (dao.insClientOrder(co)) {
					System.out.println("in1");
					int tranCount = 0;
					for (int i = 0; i < co.getOd().size(); i++) {	
						co.getOd().get(i).setOd_oscode(dao.getOrderData(co));
						if (!dao.insClientOrderDetail(co.getOd().get(i))) {
							break;
						}
						tranCount++;
					}
					System.out.println(tranCount+":"+co.getOd().size());
					if (tranCount == co.getOd().size()) {
						tran = true;
						//result = dao.getOrderData(co);
					}

				}
			}
		}
		pu.setTransactionResult(tran);

		return tran;
	}


	public List<TaxBean> clientGetTaxbillListCtl(ClientInfoBean ci) throws Exception {
		List<TaxBean> list = null;
		if(dao.isClient(ci)) {
			if(dao.isClientPwd(ci)) {
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
		if(dao.isClient(ci)) {
			if(dao.isClientPwd(ci)) {
				data = dao.getTaxBillDetail(ci);
				data.setTb_ttprice(enc.aesDecode(data.getTb_ttprice(), ci.getCl_code()) );
				data.setTb_price(enc.aesDecode(data.getTb_price(), ci.getCl_code()) );
				data.setTb_tax(enc.aesDecode(data.getTb_tax(), ci.getCl_code()) );
			}
		}
		return data;
	}
}