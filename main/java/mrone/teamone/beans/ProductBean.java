
package mrone.teamone.beans;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductBean {
	private String pr_spcode;
	private String pr_code;
	private String pr_stock;
	private String pr_name;
	private String pr_image;
	private String pr_price;
	private String pr_tax;
	private String pr_info;
	private String pr_origin;
	private String pr_spbkind;
	private String pr_stcode;
	private String cate;
	private String cate_name;
	private String word; //검색어
	private String sp_name;
	private String sp_tel;
	private String sp_address;
	private String st_name;	
	private MultipartFile file;
}
