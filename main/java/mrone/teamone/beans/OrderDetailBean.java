package mrone.teamone.beans;

import lombok.Data;

@Data
public class OrderDetailBean {
	private String OD_PRSPCODE;
	private String OD_OSCODE;
	private String OD_STCODE;
	private String OD_PRCODE;
	private int OD_QUANTITY;
}
