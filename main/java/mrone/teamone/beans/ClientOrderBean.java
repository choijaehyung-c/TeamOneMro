package mrone.teamone.beans;

import java.util.List;

import lombok.Data;

@Data
public class ClientOrderBean {
	private String os_code;
	private String os_clcode;
	private String cl_pwd;
	private String os_date;
	private String os_state;
	private String sp_code;
	private String os_origin;
	private String os_region;
	private List<OrderDetailBean> od;
}