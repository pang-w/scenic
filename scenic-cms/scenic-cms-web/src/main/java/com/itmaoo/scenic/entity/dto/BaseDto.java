package com.itmaoo.scenic.entity.dto;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class BaseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public String toJsonString() {
		return JSONObject.fromObject(this).toString();
	}

}