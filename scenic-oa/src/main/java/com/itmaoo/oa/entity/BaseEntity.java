package com.itmaoo.oa.entity;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;


public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public String toJsonString() {
		return JSONObject.toJSONString(this);
	}

}
