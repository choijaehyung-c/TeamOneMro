package mrone.client.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import mrone.teamone.beans.ClientOrderBean;
import mrone.teamone.utill.ProjectUtils;

@Service
public class ClientServiceCtl {
	@Autowired
	ClientDaoCJH dao;
	@Autowired
	ProjectUtils pu;
	
	
	String clientRequestCtl(ClientOrderBean co){
		String result = "failure";
		boolean tran = false;
		pu.setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);
		if(dao.isClient(co)) {
			if(dao.isClientPwd(co)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
				Calendar cal = Calendar.getInstance();
				co.setOS_DATE(sdf.format(cal.getTime()));
				if(dao.insClientOrder(co)) {
					int tranCount = 0;
					for(int i = 0 ; i <co.getOD().size(); i++) {
						co.getOD().get(i).setOD_OSCODE(dao.getOrderData(co));
						if(!dao.insClientOrderDetail(co.getOD().get(i))) {
							break;
						}
						tranCount++;
					}
					if(tranCount == co.getOD().size()) {
						tran = true;
						result = dao.getOrderData(co);
					}
					
				}				
			}
		}
		pu.setTransactionResult(tran);
		return result;
	}

}
/*
 * setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);
 * setTransactionResult(tran);
 * 
 * 
 * SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
			Calendar cal = Calendar.getInstance();
			td.setTeCode(sdf.format(cal.getTime())+strIndex);
 * 
 * 	private String OS_CODE;
	private String OS_CLCODE;
	private String CL_PWD;
	private String OS_DATE;
	private String OS_STATE;
	private String SP_CODE;
	private List<OrderDetailBean> OD;
 * 
 * 
 * 
 * 	private String OD_PRSPCODE;
	private String OD_OSCODE;
	private String OD_STCODE;
	private String OD_PRCODE;
	private int OD_QUANTITY;
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * */
 