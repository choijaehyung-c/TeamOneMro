package mrone.teamone.beans;

import lombok.Data;

@Data
public class ClientInfoBean {
	private String cl_code; //인트라넷에서 OF_CODE
	private String cl_pwd; 
	private String cl_name;
	private String cl_address;
	private String cl_corpnum; //회사 사업자번호
	private String cl_hp;
	private String cl_btype; 
	private String cl_bkind;
	private String bk_name; //업종이름 ex. 일반건설업, 사무용품...
	private String tb_code;
	private String cl_email;
}
