package mrone.supply.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import mrone.teamone.beans.MroOrderBean;
import mrone.teamone.beans.MroOrderDetailBean;
import mrone.teamone.utill.ProjectUtils;

@Service
public class SupplyServiceIYJ {

	private ModelAndView mav =null;

	@Autowired
	SupplyDaoIYJ dao;
	@Autowired
	private ProjectUtils pu;

	@Autowired
	DataSourceTransactionManager tx; 

	private DefaultTransactionDefinition def; //isolation과 propagation을 위한
	private TransactionStatus status;

	MroOrderDetailBean mod;

	public List<MroOrderBean> supplyReceiveRefundListForm() {
		mod= new MroOrderDetailBean();
		List<MroOrderBean> list;

		mod.setOd_prspcode("KR001D");//Session이 없어서 강제로 넣어줌
		mod.setOs_state("RR");

		list = dao.getRefundListSp(mod);

		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getOs_state().equals("RR")) {
				list.get(i).setOs_state("반품요청");
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

	public ModelAndView supplyReceiveExchangeListForm() {
		mav = new ModelAndView();
		mod= new MroOrderDetailBean();
		List<MroOrderDetailBean> list;

		mod.setOd_prspcode("KR001D"); //Session이 없어서 강제로 넣어줌(교환있는 회사)
		mod.setOs_state("ER");
		mav.setViewName("MroHome");
		mav.addObject("exchangeList",dao.getRefundListSp(mod));
		//System.out.println(dao.getRefundListSp(mod));

		return mav;
	}

	//반품 또는 교환 디테일
	public List<MroOrderDetailBean> supplyReceiveAsDetail(MroOrderBean mo){
		List<MroOrderDetailBean> list;

		list = dao.supplyReceiveAsDetail(mo);

		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getOd_stcode().equals("RR")) {
				list.get(i).setOd_stcode("반품요청");
			}else if(list.get(i).getOd_stcode().equals("OC")) {
				list.get(i).setOd_stcode("구매확정");
			}else if(list.get(i).getOd_stcode().equals("RC")){
				list.get(i).setOd_stcode("반품처리");
			}else if(list.get(i).getOd_stcode().equals("OR")) {
				list.get(i).setOd_stcode("주문요청");
			}
		}

		return list;
	}

	//공급사 - 반품에 대한 응답(거절(FF)or수락(RC))
	public String supplyResponseRefund(MroOrderBean mo) {
		String message="";
		String originOsCode= mo.getOs_code();
		this.setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED ,false);

		//System.out.println(mo);
		if(mo.getOs_state().equals("RC")) {
			System.out.println("수락");
			//원래 있었던 주문코드 OD : RR->RC로 업데이트됐고
			if(dao.supplyResponseRefund(mo)) {
				//원래 있었언 주문코드 OS : RR->RC 업데이트 됐고,
				if(dao.supplyResponseRefundOS(mo)) {
					//새로운 주문 코드 생성OS
					if(this.insNewOrders(mo)) {
						System.out.println("ins성공!!!");
						this.setTransactionResult(true); // commit완료
						//새로운 주문코드 OD
						if(this.insNewOrderDetail(originOsCode)) {
							message="반품요청이 정상적으로 처리되었습니다.";
							System.out.println("반품처리 완성");

						}
					}else {
						this.setTransactionResult(false); //rollback
					}
				}
			}else {
				System.out.println("업데이트 실패");
				message = "반품처리에 실패했습니다. 잠시후 다시 시도해주세요";
			}


		}else {
			//RR-> FF로 업데이트
			if(dao.supplyResponseRefund(mo)) {
				System.out.println("거절");
				message = "반품요청이 거절되었습니다.";
			}
		}		
		return message;
	}


	public boolean insNewOrders(MroOrderBean mo) {
		List<MroOrderBean> list;
		boolean insert= false;

		//그 주문코드에 OC가 있다면 OS에 OC로 삽입
		list = dao.supplyOSInfo(mo);
		mo.setOs_code(dao.getCount());
		mo.setOs_clcode(list.get(0).getOs_clcode());
		mo.setOs_state("OC");

		System.out.println("insNewOS : "+mo);
		if(dao.insNewOrders(mo)) {
			System.out.println("ins성공");
			insert=true;


		}

		return insert;
	}


	public boolean insNewOrderDetail(String osCode) {//Boolean
		List<MroOrderDetailBean> mod = new ArrayList<MroOrderDetailBean>();
		List<MroOrderDetailBean> ocList;
		boolean insert= false;
		System.out.println("insOD : "+osCode);

		//그 주문코드에 OC가 있다면 
		ocList = dao.supplyOCInfo(osCode);

		System.out.println("초기 :  "+ocList);
		for(int i=0; i<ocList.size(); i++) {		
			if(ocList.get(i).getOd_stcode().equals("OC")) {
				//새로운 주문코드를 만든다. 그 주문코드의 oc의 정보들을 넣기 od,os인설트
				MroOrderDetailBean result  = new MroOrderDetailBean();
				result.setOd_oscode(dao.checkCount());
				result.setOd_prspcode(ocList.get(i).getOd_prspcode());
				result.setOd_prcode(ocList.get(i).getOd_prcode());
				result.setOd_quantity(ocList.get(i).getOd_quantity());
				result.setOd_stcode(ocList.get(i).getOd_stcode());

				mod.add(result);

			}else {
				System.out.println("새로운 주문서번호가없음");

			}		
		} 

		if(dao.insNewOrderDetail(mod)) { // 새로운 주문서번호 생성으로 OD INSERT가 됐으면
			System.out.println(mod);
			insert = true;
		}
		return insert;
	}


	//transaction Configuration
	private void setTransactionConf(int propagation, int isolationLevel, boolean isRead) {
		def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(propagation);
		def.setIsolationLevel(isolationLevel);
		def.setReadOnly(isRead);

		status = tx.getTransaction(def);
	}

	//Transaction Result , commit이냐 rollback이냐
	private void setTransactionResult(boolean isCheck) {
		if(isCheck) {
			tx.commit(status);

		}else {
			tx.rollback(status);
		}
	}

}
