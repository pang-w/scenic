package com.itmaoo.scenic.entity.dto;

import java.util.Date;
/**
 * 
 * @author mario
 *
 */
public class ImageDto extends BaseDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7711473873175719666L;
	private Integer id;
	private String description;
	private String baseUri;
	private String baseNum;
	private String linkTo;
	private String type;
	
	private String username;
	private String imageName;
	
	private String url;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBaseUri() {
		return baseUri;
	}
	public void setBaseUri(String baseUri) {
		this.baseUri = baseUri;
	}
	public String getBaseNum() {
		return baseNum;
	}
	public void setBaseNum(String baseNum) {
		this.baseNum = baseNum;
	}
	public String getLinkTo() {
		return linkTo;
	}
	public void setLinkTo(String linkTo) {
		this.linkTo = linkTo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
