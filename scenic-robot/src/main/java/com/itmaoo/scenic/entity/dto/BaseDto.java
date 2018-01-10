package com.itmaoo.scenic.entity.dto;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class BaseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String createDate;
	private String lastModifyDate;
	
	private Integer pageIndex;


	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}


	public String getLastModifyDate() {
		return lastModifyDate;
	}



	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}



	public String toJsonString() {
		return JSONObject.fromObject(this).toString();
	}

}