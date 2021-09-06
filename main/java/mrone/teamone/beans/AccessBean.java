package mrone.teamone.beans;

import lombok.Data;

@Data
public class AccessBean {
	private String id;
	private String pwd;
	private String code; //서플라이 회사코드
	private String table;
	private String col;
	private String col2;
}
