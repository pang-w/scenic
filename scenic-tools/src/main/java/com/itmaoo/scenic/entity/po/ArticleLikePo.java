package com.itmaoo.scenic.entity.po;

public class ArticleLikePo  extends BasePo{
	private static final long serialVersionUID = -6627269735790801181L;
	
	private String articleUuid;
	private String actionUser;
	

	public String getArticleUuid() {
		return articleUuid;
	}
	public void setArticleUuid(String articleUuid) {
		this.articleUuid = articleUuid;
	}
	public String getActionUser() {
		return actionUser;
	}
	public void setActionUser(String actionUser) {
		this.actionUser = actionUser;
	}
	

}
