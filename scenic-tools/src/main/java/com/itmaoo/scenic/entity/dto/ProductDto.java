package com.itmaoo.scenic.entity.dto;

/**
 * 实体类 - 商品
 **/

public class ProductDto extends BaseDto {

	private static final long serialVersionUID = 4858058186018438872L;
	
	private Integer id;
	private String name;
	private String imgUrl;
	private String price;
	private String description;
	private String linkTo;
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getLinkTo() {
		return linkTo;
	}
	public void setLinkTo(String linkTo) {
		this.linkTo = linkTo;
	}
	

}