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

import mrone.teamone.beans.DeliveryBean;
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

	public List<MroOrderBean> supplyReceiveExchangeListForm() {
		mod= new MroOrderDetailBean();
		List<MroOrderBean> list;

		mod.setOd_prspcode("KR001D"); //Session이 없어서 강제로 넣어줌(교환있는 회사)
		mod.setOs_state("ER");

		list = dao.getRefundListSp(mod);

		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getOs_state().equals("ER")) {
				list.get(i).setOs_state("교환요청");
			}
		}

		return list;
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
			}else if(list.get(i).getOd_stcode().equals("ER")) {
				list.get(i).setOd_stcode("교환요청");
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
		if(mo.getOs_state().equals("PD")) {
			System.out.println("수락");
			//1. OD 테이블 : 오리지널 주문코드 RR-> PD(폐기)
			if(dao.supplyResponseRefund(mo)) {
				//2. OS 테이블 : 오리지널 주문코드  RR->PD(폐기),
				if(dao.supplyResponseRefundOS(mo)) {
					//3. OS 테이블 : 새로운 주문코드 추가(parent먼저)(구매확정)
					if(this.insNewOrdersOC(mo)) {
						System.out.println("ins성공!!!");					
						//4. OD 테이블 : 새로운 주문코드 추가(구매확정)
						if(this.insNewOrderDetailOC(originOsCode)) {
							//5. OS 테이블 : 새로운 주문코드 추가(반품처리)
							if(this.insNewOrderRC(mo)) {
								//6. OD 테이블 : 새로운 주문코드 추가 (반품처리)
								if(this.insNewOrderDetailRC(originOsCode)) {
									message="반품요청이 정상적으로 처리되었습니다.";
									System.out.println("반품처리 완성");
									this.setTransactionResult(true); // commit완료
								}
							}
						}
					}
				}
			}else {
				this.setTransactionResult(false); //rollback
				System.out.println("업데이트 실패");
				message = "반품처리에 실패했습니다. 잠시후 다시 시도해주세요";
			}


		}else {
			//RR-> FF로 업데이트
			System.out.println(mo);
			if(dao.supplyResponseRefund(mo)) {
				System.out.println("거절");
				message = "반품요청이 거절되었습니다.";
			}
		}		
		return message;
	}


	//3. OS테이블에 OC구매확정된 레코드 추가
	private boolean insNewOrdersOC(MroOrderBean mo) {
		List<MroOrderBean> list;
		boolean insert= false;

		//그 주문코드에 OC가 있다면 OS에 OC로 삽입
		list = dao.supplyOSInfo(mo);
		//mo.setOs_code(dao.getCount());
		mo.setOs_clcode(list.get(0).getOs_clcode());
		mo.setOs_state("OC");

		System.out.println("OS테이블 구매확정레코드 : "+mo);
		if(dao.insNewOrders(mo)) {
			System.out.println("구매확정 레코드 추가 성공(OS)");
			insert=true;


		}
		return insert;
	}


	//4. OD에 구매확정된 애들이 모여있는 레코드 추가
	private boolean insNewOrderDetailOC(String osCode) {//Boolean
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
				result.setOd_stcode("OC");

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



	//5. 반품처리 된 녀석들을 위한 OS테이블에 주문코드가 생김.
	private boolean insNewOrderRC(MroOrderBean mo) {
		List<MroOrderBean> list;
		boolean insert= false;

		//그 주문코드에 OC가 있다면 OS에 OC로 삽입
		list = dao.supplyOSInfo(mo);
		//mo.setOs_code(dao.getCount());
		mo.setOs_clcode(list.get(0).getOs_clcode());
		mo.setOs_state("RA");

		System.out.println("OS테이블 반품처리 레코드 : "+mo);
		if(dao.insNewOrders(mo)) {
			System.out.println("반품처리 레코드 성공 추가 성공(OS)");
			insert=true;
		}

		return insert;
	}

	//6. OD테이블에 RC를 위한 주문코드 레코드 추가
	private boolean insNewOrderDetailRC(String osCode) {
		List<MroOrderDetailBean> mod = new ArrayList<MroOrderDetailBean>();
		List<MroOrderDetailBean> ocList;
		boolean insert= false;

		System.out.println("insOD : "+osCode);

		//그 주문코드에 PD가 있다면 
		ocList = dao.supplyPDInfo(osCode);

		System.out.println("초기 :  "+ocList);
		for(int i=0; i<ocList.size(); i++) {		
			if(ocList.get(i).getOd_stcode().equals("PD")) {
				//새로운 주문코드를 만든다. 그 주문코드의 PD의 정보들을 넣기 od,os인설트
				MroOrderDetailBean result  = new MroOrderDetailBean();
				result.setOd_oscode(dao.checkCount());
				result.setOd_prspcode(ocList.get(i).getOd_prspcode());
				result.setOd_prcode(ocList.get(i).getOd_prcode());
				result.setOd_quantity(ocList.get(i).getOd_quantity());
				result.setOd_stcode("RA");

				mod.add(result);

			}else {
				System.out.println("새로운 주문서번호가없음");

			}		
		} 

		if(dao.insNewOrderDetail(mod)) { // 새로운 주문서번호 생성으로 OD INSERT가 됐으면
			insert = true;
		}
		return insert;
	}


	//교환 요청에 대한 응답 
	public String supplyResponseExchange(MroOrderBean mo) {
		String message="";
		DeliveryBean db = new DeliveryBean();
		db.setDl_oscode(mo.getOs_code());
		db.setDl_dvcode("IYJ032");
		db.setDl_dscode("1");


		//교환요청에 수락이면
		if(mo.getOs_state().equals("EA")) {
			//OD 테이블 :  오리지널 주문코드 ER-> EC(폐기)
			if(dao.supplyResponseExchangeOD(mo)) {
				if(dao.supplyResponseExchangeOS(mo)) {
					if(dao.makeDeliveryLocate()) {
						db.setDl_lccode(dao.maxLCcode());//마지막 운송장번호를 가져와서 lcCode에넣는다.								
						if(dao.supplyAskDelivery(db)) { //운송장번호 발급
							message="교환요청이 정상적으로 처리되었습니다.";
							System.out.println("교환수락");
						}
					}
				}
			}
		}else {//교환요청이 거절이면 (EE)
			if(dao.supplyResponseExchangeOD(mo)) {
				if(dao.supplyResponseExchangeOS(mo)) {
					message="교환요청이 거절 처리되었습니다.";
					System.out.println("교환거절");
				}
			}

		}


		return message;
	}


	//검색결과
	public List<MroOrderBean> supplySearchAs(MroOrderBean mo) {
		List<MroOrderBean> list;
		list = dao.supplySearchAs(mo);

		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getOs_state().equals("OR")) {
				list.get(i).setOs_state("주문요청");
			}if(list.get(i).getOs_state().equals("RR")) {
				list.get(i).setOs_state("반품요청");
			}if(list.get(i).getOs_state().equals("ER")) {
				list.get(i).setOs_state("교환요청");
			}if(list.get(i).getOs_state().equals("OC")) {
				list.get(i).setOs_state("구매확정");
			}

		}


		return list;
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
