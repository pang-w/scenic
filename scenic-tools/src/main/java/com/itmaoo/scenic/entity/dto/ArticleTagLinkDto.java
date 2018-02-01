package com.itmaoo.scenic.entity.dto;

public class ArticleTagLinkDto extends BaseDto {

	private static final long serialVersionUID = 6365091150016172187L;
	
	private String articleUuid;
	private String tagId;
	private String createBy;
	
	public String getArticleUuid() {
		return articleUuid;
	}
	public void setArticleUuid(String articleUuid) {
		this.articleUuid = articleUuid;
	}
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	

}
