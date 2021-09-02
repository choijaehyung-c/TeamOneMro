package mrone.teamone.beans;

import lombok.Data;

@Data
//MRO가 주문대기리스트를 확인하기위한 Bean
public class MroOrderBean {
	
	private String os_code;
	private String os_clcode;
	private String os_date;
	private String os_state;
	private String cl_name;

}
