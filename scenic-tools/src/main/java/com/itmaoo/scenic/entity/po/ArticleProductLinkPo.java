package com.itmaoo.scenic.entity.po;

public class ArticleProductLinkPo extends BasePo {

	private static final long serialVersionUID = 6365091150016172187L;
	
	private String articleUuid;
	private Integer productId;
	private Boolean effected;
	
	public String getArticleUuid() {
		return articleUuid;
	}
	public void setArticleUuid(String articleUuid) {
		this.articleUuid = articleUuid;
	}

	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Boolean getEffected() {
		return effected;
	}
	public void setEffected(Boolean effected) {
		this.effected = effected;
	}
	

	

}
