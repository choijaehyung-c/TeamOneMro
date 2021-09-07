package mrone.teamone.beans;
import lombok.Data;



//MRO에서 주문대기 내역 상세조회할때 쓰는 빈
@Data
public class MroOrderDetailBean {

	private String od_prspcode; //공급사코드
	private String sp_name; //공급사이름
	private String od_oscode; //주문코드
	private String od_prcode; //상품코드
	private String pr_name; //상품이름
	private String od_quantity; //주문갯수
	private String os_state;//상태
	private String od_stcode;//od테이블의 상태
	private String os_clcode;

}

