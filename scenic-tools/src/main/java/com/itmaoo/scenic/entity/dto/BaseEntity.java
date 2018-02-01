package com.itmaoo.scenic.entity.dto;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author mario
 *
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public String toJsonString() {
		return JSONObject.toJSONString(this);
	}

}
