
package mrone.teamone.beans;

import java.util.List;

import lombok.Data;

@Data
public class RequestOrderBean {
	private String re_code;
	private String re_oscode;
	private String re_clcode;
	private String rd_note;
	private String cl_name;
	private String re_spcode;
	private String re_date;
	private String re_state;
	private String word;
	private String cl_hp;
	private String re_origin;
	private List<RequestOrderDetailBean> rd;
}