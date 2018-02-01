package com.itmaoo.scenic.entity.po;

public class ArticleTagLinkPo extends BasePo {

	private static final long serialVersionUID = 6365091150016172187L;
	
	private String articleUuid;
	private Integer tagId;
	private String createBy;
	private Boolean effected;
	

	public Boolean getEffected() {
		return effected;
	}
	public void setEffected(Boolean effected) {
		this.effected = effected;
	}
	public String getArticleUuid() {
		return articleUuid;
	}
	public void setArticleUuid(String articleUuid) {
		this.articleUuid = articleUuid;
	}

	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	

}
