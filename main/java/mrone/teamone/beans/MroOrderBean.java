package mrone.teamone.beans;

import lombok.Data;

@Data
//MRO가 주문대기리스트를 확인하기위한 Bean
public class MroOrderBean {
	   
	   private String os_code;
	   private String os_clcode;
	   private String os_date;
	   private String os_state; //상태 - 대기, 배송완료,  반품요청, 반품완료, 교환요청, 교환완료 
	   private String cl_name;
	   private String word;
	   private String od_prspcode;
	   private String sp_name;
	  
}
