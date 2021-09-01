package mrone.teamone.beans;

import lombok.Data;

@Data
public class SupplyInfoBean {
	
	private String SP_CODE;
	private String SP_NAME;
	private String SP_ADDRESS;
	private String SP_TEL;
	private String SP_CORPNUM;
	private String SP_BTYPE;
	private String SP_BKIND;
	private String BK_NAME; //업종이름 (ex.사무용품, 청소용품...)
	

}
