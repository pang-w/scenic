package com.itmaoo.scenic.entity.query;

/**
 * 实体类 - 商品
 **/

public class ProductQuery extends BaseQuery {

	private static final long serialVersionUID = 4858058186018438872L;
	
	private Integer id;
	private String name;
	private String imgUrl;
	private String thdPartyUrl;
	private String price;
	private String description;
	
	private String username;
	private String articleUuid;
	
	public String getArticleUuid() {
		return articleUuid;
	}
	public void setArticleUuid(String articleUuid) {
		this.articleUuid = articleUuid;
	}
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
	public String getThdPartyUrl() {
		return thdPartyUrl;
	}
	public void setThdPartyUrl(String thdPartyUrl) {
		this.thdPartyUrl = thdPartyUrl;
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

	

}