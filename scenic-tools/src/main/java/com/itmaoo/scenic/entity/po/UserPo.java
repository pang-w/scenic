package com.itmaoo.scenic.entity.po;

import java.util.Date;

/**
 * 
 * @author mario
 *
 */
public class UserPo extends BasePo{
	private static final long serialVersionUID = 6194632578192753293L;
	private Integer userid;
	private String username;
	private String password; 
	private String email;
	private String telphone;
	private String iuCode;
	private String recCode;
	private Boolean invalid;
	private Boolean isMale;
	private Integer loginTryTimes; 
	private Date lastLoggedDate;
	private String signature;
	private String nickname;
	private Integer signatureLikedCount;
	private String alipayImgUrl;
	private String weixinImgUrl;
	
	
	public String getAlipayImgUrl() {
		return alipayImgUrl;
	}
	public void setAlipayImgUrl(String alipayImgUrl) {
		this.alipayImgUrl = alipayImgUrl;
	}
	public String getWeixinImgUrl() {
		return weixinImgUrl;
	}
	public void setWeixinImgUrl(String weixinImgUrl) {
		this.weixinImgUrl = weixinImgUrl;
	}
	public Integer getSignatureLikedCount() {
		return signatureLikedCount;
	}
	public void setSignatureLikedCount(Integer signatureLikedCount) {
		this.signatureLikedCount = signatureLikedCount;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getIuCode() {
		return iuCode;
	}
	public void setIuCode(String iuCode) {
		this.iuCode = iuCode;
	}
	public String getRecCode() {
		return recCode;
	}
	public void setRecCode(String recCode) {
		this.recCode = recCode;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public Integer getLoginTryTimes() {
		return loginTryTimes;
	}
	public void setLoginTryTimes(Integer loginTryTimes) {
		this.loginTryTimes = loginTryTimes;
	}

	public Boolean getInvalid() {
		return invalid;
	}
	public void setInvalid(Boolean invalid) {
		this.invalid = invalid;
	}

	public Date getLastLoggedDate() {
		return lastLoggedDate;
	}
	public void setLastLoggedDate(Date lastLoggedDate) {
		this.lastLoggedDate = lastLoggedDate;
	}
	public Boolean getIsMale() {
		return isMale;
	}
	public void setIsMale(Boolean isMale) {
		this.isMale = isMale;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	

}
