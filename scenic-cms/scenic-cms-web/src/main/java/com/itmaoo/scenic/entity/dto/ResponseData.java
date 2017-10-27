package com.itmaoo.scenic.entity.dto;

/**
 * 
 * @author mario
 *
 */
public class ResponseData extends BaseDto{
	private static final long serialVersionUID = -614207446823812368L;
	private String status;
	private String msg;
	private Object data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
