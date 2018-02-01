package com.itmaoo.scenic.entity.po;

import java.io.Serializable;
import java.util.Date;

public class BasePo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date createDate;
	private Date lastModifyDate;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	
	
}