package mrone.teamone.beans;

import lombok.Data;

@Data
public class AccessHistoryBean {
	private String ah_table;//type
	private String ah_sdspcode;//spcode
	private String ah_code;//id
	private String ah_publicip;
	private String ah_privateip;
	private String ah_browser;
	private String ah_method;
	private String ah_date;
	private String ah_pwd;
	private String ck;
}
