
package mrone.teamone.beans;

import java.util.List;

import lombok.Data;

@Data
public class RequestOrderBean {
	private String re_code;
	private String re_clcode;
	private String re_spcode;
	private String re_date;
	private String re_state;
	private List<OrderDetailBean> detail;
}
