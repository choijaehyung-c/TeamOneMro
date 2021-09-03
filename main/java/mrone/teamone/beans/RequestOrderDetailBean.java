package mrone.teamone.beans;


import java.util.List;

import lombok.Data;

@Data
public class RequestOrderDetailBean {
		private String rd_prspcode;
		private String rd_recode;
		private String rd_prcode;
		private String rd_quantity;
		private String rd_stcode;
		List<RequestOrderDetailBean> detail;
}
