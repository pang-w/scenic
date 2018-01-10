package com.itmaoo.scenic.entity.dto;

public class MessageLikeDto  extends BaseDto{
	private static final long serialVersionUID = -6627269735790801181L;
	
	private Integer messageId;
	private String actionUser;


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

	

}
