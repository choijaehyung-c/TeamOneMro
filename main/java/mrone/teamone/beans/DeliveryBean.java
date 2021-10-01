package mrone.teamone.beans;

import lombok.Data;

@Data
public class DeliveryBean {
	private String dl_code;
	private String dl_oscode;
	private String dl_dscode;
	private String ds_name;
	private String dl_dvcode;
	private String dv_name;
	private String dv_hp;
	private String dl_lccode;
	private double lc_x;
	private double lc_y;
	private String lc_date;
	private String dv_spcode;
	private String sp_address;
	private String sp_tel;
	private String sp_name;
}
