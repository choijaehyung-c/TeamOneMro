package mrone.teamone.beans;

import lombok.Data;

@Data
public class OrderDetailBean {
	private String od_prspcode;
	private String od_oscode;
	private String od_stcode;
	private String od_prcode;
	private int od_quantity;
}
