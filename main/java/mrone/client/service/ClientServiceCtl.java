package mrone.client.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;

import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.ClientOrderBean;
import mrone.teamone.beans.TaxBean;
import mrone.teamone.utill.Encryption;
import mrone.teamone.utill.ProjectUtils;

@Service
public class ClientServiceCtl {
	@Autowired
	ClientDaoCJH dao;
	@Autowired
	ProjectUtils pu;
	@Autowired
	Encryption enc;
	
	
	String clientRequestCtl(ClientOrderBean co){
		String result = "failure";
		/*
		 * boolean tran = false;
		 * pu.setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,
		 * TransactionDefinition.ISOLATION_READ_COMMITTED,false); ClientInfoBean ci =
		 * new ClientInfoBean(); ci.setCL_CODE(co.getOS_CLCODE()); try {
		 * System.out.println(co.getOS_CLCODE() + ":::");
		 * ci.setCL_PWD(enc.aesEncode(co.getCL_PWD(), co.getOS_CLCODE()) );
		 * System.out.println(ci.getCL_PWD()); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * if(dao.isClient(ci)) { if(dao.isClientPwd(ci)) { SimpleDateFormat sdf = new
		 * SimpleDateFormat("yyMMddHHmmss"); Calendar cal = Calendar.getInstance();
		 * co.setOS_DATE(sdf.format(cal.getTime())); if(dao.insClientOrder(co)) { int
		 * tranCount = 0; for(int i = 0 ; i <co.getOD().size(); i++) {
		 * co.getOD().get(i).setOD_OSCODE(dao.getOrderData(co));
		 * co.getOD().get(i).setOD_STCODE(co.getOS_STATE());
		 * if(!dao.insClientOrderDetail(co.getOD().get(i))) { break; } tranCount++; }
		 * if(tranCount == co.getOD().size()) { tran = true; result =
		 * dao.getOrderData(co); }
		 * 
		 * } } } pu.setTransactionResult(tran);
		 */
		return result;
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

/* taxbillList 일단 가져온것
 * 세금계산서코드 주문서코드 공급사이름 공급날짜
 *  */
 