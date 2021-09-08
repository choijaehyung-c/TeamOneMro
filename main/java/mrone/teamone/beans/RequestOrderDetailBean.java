package mrone.teamone.beans;


import java.util.List;

import lombok.Data;

@Data
public class RequestOrderDetailBean {
		private String rd_prspcode;
		private String sp_name;
		private String rd_recode;
		private String rd_prcode;
		private String pr_name;
		private String pr_image;
		private String pr_info;
		private String pr_origin;
		private String cate_name;
		private int pr_price;
		private int pr_tax;
		private int pr_ttprice;
		private int rd_quantity;
		private String rd_stcode;
		private String rd_note;
		List<RequestOrderDetailBean> detail;//?
}