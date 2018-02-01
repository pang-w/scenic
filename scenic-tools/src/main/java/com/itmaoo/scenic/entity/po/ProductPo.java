package com.itmaoo.scenic.entity.po;

/**
 * 实体类 - 商品
 **/

public class ProductPo extends BasePo {

	private static final long serialVersionUID = 4858058186018438872L;
	
	private String name;
	private String imgUrl;
	private String linkTo;
	private String price;
	private String description;
	private String baseNum;
	private String username;
	private String type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getLinkTo() {
		return linkTo;
	}
	public void setLinkTo(String linkTo) {
		this.linkTo = linkTo;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBaseNum() {
		return baseNum;
	}
	public void setBaseNum(String baseNum) {
		this.baseNum = baseNum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	

}