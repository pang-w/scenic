package com.itmaoo.scenic.entity.query;

import com.itmaoo.scenic.entity.dto.BaseDto;

public class ArticleProductLinkQuery extends BaseDto {

	private static final long serialVersionUID = 6365091150016172187L;
	
	private String articleUuid;
	private String productId;
	private Boolean effected;
	
	public String getArticleUuid() {
		return articleUuid;
	}
	public void setArticleUuid(String articleUuid) {
		this.articleUuid = articleUuid;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Boolean getEffected() {
		return effected;
	}
	public void setEffected(Boolean effected) {
		this.effected = effected;
	}
	

	

}
