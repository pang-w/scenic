package com.itmaoo.scenic.entity.dto;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;

public class BaseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String createDate;
	private String lastModifyDate;
	
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