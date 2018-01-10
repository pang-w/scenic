package com.itmaoo.scenic.entity.query;

import java.util.Date;

public class MessageLikeQuery  extends BaseQuery{
	private static final long serialVersionUID = -6627269735790801181L;
	
	private Integer id;
	private Integer messageId;
	private String actionUser;
	private Date createDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
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
