package mrone.teamone.beans;
import lombok.Data;



//MRO에서 주문대기 내역 상세조회할때 쓰는 빈
@Data
public class MroOrderDetailBean {

	private String OD_PRSPCODE; //공급사코드
	private String SP_NAME; //공급사이름
	private String OD_OSCODE; //주문코드
	private String OD_PRCODE; //상품코드
	private String PR_NAME; //상품이름
	private String OD_QUANTITY; //주문갯수

}

