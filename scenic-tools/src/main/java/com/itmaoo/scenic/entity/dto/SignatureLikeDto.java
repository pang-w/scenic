package com.itmaoo.scenic.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class SignatureLikeDto  implements Serializable{
	private static final long serialVersionUID = -6627269735790801181L;
	
	private Integer id;
	private String belikedUser;
	private String actionUser;
	private Date createDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBelikedUser() {
		return belikedUser;
	}
	public void setBelikedUser(String belikedUser) {
		this.belikedUser = belikedUser;
	}
	public String getActionUser() {
		return actionUser;
	}
	public void setActionUser(String actionUser) {
		this.actionUser = actionUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	

}
