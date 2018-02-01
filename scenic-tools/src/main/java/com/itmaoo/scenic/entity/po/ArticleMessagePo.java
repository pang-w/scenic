package com.itmaoo.scenic.entity.po;

/**
 * 实体类 - 商品
 **/

public class ArticleMessagePo extends BasePo {

	private static final long serialVersionUID = 4858058186018438872L;
	
	private String actionUser;
	private String articleUuid;
	private String message;
	//from articlelike count
	private Integer likedCount;
	

	public Integer getLikedCount() {
		return likedCount;
	}
	public void setLikedCount(Integer likedCount) {
		this.likedCount = likedCount;
	}
	public String getActionUser() {
		return actionUser;
	}
	public void setActionUser(String actionUser) {
		this.actionUser = actionUser;
	}
	public String getArticleUuid() {
		return articleUuid;
	}
	public void setArticleUuid(String articleUuid) {
		this.articleUuid = articleUuid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


	
	

}