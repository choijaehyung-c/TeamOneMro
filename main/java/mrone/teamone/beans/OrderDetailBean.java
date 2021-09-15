package mrone.teamone.beans;

import lombok.Data;

@Data
public class OrderDetailBean {
	private String od_prspcode;
	private String od_oscode;
	private String od_stcode;
	private String od_prcode;
	private String od_note;
	private int od_quantity;
	private String pr_name;
	private int pr_price;
	private int pr_tax;
}
