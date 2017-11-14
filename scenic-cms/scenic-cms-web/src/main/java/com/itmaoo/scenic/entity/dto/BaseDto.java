package com.itmaoo.scenic.entity.dto;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class BaseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String createDate;
	private String lastModifyDate;
	
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