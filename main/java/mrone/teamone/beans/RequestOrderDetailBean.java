package mrone.teamone.beans;


import java.util.List;

import lombok.Data;

@Data
public class RequestOrderDetailBean {
		private String rd_prspcode;
		private String sp_name;
		private String rd_recode;
		private String pr_code;
		private String pr_name;
		private int pr_price;
		private int pr_tax;
		private int rd_quantity;	
		private String rd_stcode; //?
		private String rd_note;
		List<RequestOrderDetailBean> detail;//?
}