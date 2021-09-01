package mrone.teamone.beans;

import lombok.Data;

@Data
public class ClientInfoBean {
	private String 	CL_CODE; //인트라넷에서 OF_CODE
	private String  CL_PWD; 
	private String  CL_NAME;
	private String  CL_ADDRESS;
	private String  CL_CORPNUM; //회사 사업자번호
	private String  CL_HP;
	private String  CL_BTYPE; 
	private String  CL_BKIND;
	private String  BK_NAME; //업종이름 ex. 일반건설업, 사무용품...
}
