package mrone.teamone.beans;

import lombok.Data;

@Data
//MRO가 주문대기리스트를 확인하기위한 Bean
public class MroOrderBean {
	
	private String OS_CODE;
	private String OS_CLCODE;
	private String OS_DATE;
	private String OS_STATE;
	private String CL_NAME;

}
