package com.itmaoo.scenic.entity.query;

/**
 * 
 * @author mario
 *
 */
public class ArticleQuery extends BaseQuery {
	private static final long serialVersionUID = 1269153569283907877L;
	private Integer id;
	private String title;
	private String keyword;
	private String username;
	private String content;
	private String desc;
	private String uuid;
	private Boolean isPublished;
	private String articleUuid;

	private String tag;
	private String defaultImageUrl;

	public String getDefaultImageUrl() {
		return defaultImageUrl;
	}

	public void setDefaultImageUrl(String defaultImageUrl) {
		this.defaultImageUrl = defaultImageUrl;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getArticleUuid() {
		return articleUuid;
	}

	public void setArticleUuid(String articleUuid) {
		this.articleUuid = articleUuid;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
