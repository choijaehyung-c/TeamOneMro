package mrone.teamone.beans;

import java.util.List;

import lombok.Data;

@Data
public class ClientOrderBean {	
	
	private String uCode2;
	private String OS_CODE;
	private String OS_CLCODE;
	private String OS_DATE;
	private String OS_STATE;
	private String SP_CODE;
	private List<OrderDetailBean> OD;
}
