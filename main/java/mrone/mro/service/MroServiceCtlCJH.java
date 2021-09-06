package mrone.mro.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;

import mrone.teamone.beans.ClientInfoBean;
import mrone.teamone.beans.ClientOrderBean;
import mrone.teamone.beans.RequestOrderBean;
import mrone.teamone.utill.ProjectUtils;

@Service
public class MroServiceCtlCJH {
	@Autowired
	MroDaoCJH dao;
	@Autowired
	ProjectUtils pu;
	
	String mroRequestCtl(RequestOrderBean ro,String type){
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
						ro.getRd().get(i).setRd_stcode(ro.getRe_state());
						if (!dao.insMroRequestOrderDetail(ro.getRd().get(i))) {
							break;
						}
						tranCount++;
					}
					System.out.println(tranCount+":"+ro.getRd().size());
					if (tranCount == ro.getRd().size()) {
						tran = true;
						result =""; //dao.getOrderData(co);
					}

				}
			
		
		pu.setTransactionResult(tran);

		return result;
	}

}
