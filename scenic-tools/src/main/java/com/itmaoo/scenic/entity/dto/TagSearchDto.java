package com.itmaoo.scenic.entity.dto;

public class TagSearchDto extends BaseDto{

	private static final long serialVersionUID = 3538752717811589072L;
	
	private String username;
	private String taglike;
	private String articleUuid;
	
	private String value;
	private String createBy;
	private String description;
	private String type;
	private Integer parentId;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTaglike() {
		return taglike;
	}
	public void setTaglike(String taglike) {
		this.taglike = taglike;
	}
	public String getArticleUuid() {
		return articleUuid;
	}
	public void setArticleUuid(String articleUuid) {
		this.articleUuid = articleUuid;
	}
	
	

}
