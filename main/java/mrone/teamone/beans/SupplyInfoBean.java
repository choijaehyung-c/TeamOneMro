package mrone.teamone.beans;

import lombok.Data;

@Data
public class SupplyInfoBean {
	
	private String sp_code;
	private String sp_name;
	private String sp_address;
	private String sp_tel;
	private String sp_corpnum;
	private String sp_btype;
	private String sp_bkind;
	private String bk_name;//업종이름 (ex.사무용품, 청소용품...)
	private String bt_name;//업태이름
	private String sd_email;


}
