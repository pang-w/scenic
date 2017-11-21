package com.itmaoo.scenic.entity.dto;

import java.util.List;

/**
 * 
 * @author mario
 *
 */
public class ArticleDto extends BaseDto{

	private static final long serialVersionUID = 1194326997539222168L;
	private String title;
	private String content;
	private String uuid;
	private String createDate;
	private String lastmodify;
	private String username;
	private String description;
	private Boolean isPublished;
	
	private List<TagDto> selectedTags;
	private List<ProductDto> selectedProducts;
	
	private List<ProductDto> products;
	private List<TagDto> tags;
	
	public List<ProductDto> getProducts() {
		return products;
	}
	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}
	public List<TagDto> getTags() {
		return tags;
	}
	public void setTags(List<TagDto> tags) {
		this.tags = tags;
	}
	

	public List<TagDto> getSelectedTags() {
		return selectedTags;
	}
	public void setSelectedTags(List<TagDto> selectedTags) {
		this.selectedTags = selectedTags;
	}
	public List<ProductDto> getSelectedProducts() {
		return selectedProducts;
	}
	public void setSelectedProducts(List<ProductDto> selectedProducts) {
		this.selectedProducts = selectedProducts;
	}
	public Boolean getIsPublished() {
		return isPublished;
	}
	public void setIsPublished(Boolean isPublished) {
		this.isPublished = isPublished;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getLastmodify() {
		return lastmodify;
	}

	public void setLastmodify(String lastmodify) {
		this.lastmodify = lastmodify;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
