
package mrone.teamone.beans;

import java.util.List;

import lombok.Data;

@Data
public class RequestOrderBean {
	private String RE_CODE;
	private String RE_CLCODE;
	private String RE_SPCODE;
	private String RE_DATE;
	private String RE_STATE;
	private List<OrderDetailBean> DETAIL;
	
}
