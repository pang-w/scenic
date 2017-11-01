package com.itmaoo.scenic.entity.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author mario
 *
 */
public class UserDto implements Serializable{
	private static final long serialVersionUID = 6194632578192753293L;
	private Integer userid;
	private String username;
	private String password; 
	private String email;
	private String telpone;
	private Date createdate; 
	private String invalid; 
	private String passwordTryTimes; 
	private Date lastLoggedDate;
	
	
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
	public String getTelpone() {
		return telpone;
	}
	public void setTelpone(String telpone) {
		this.telpone = telpone;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getInvalid() {
		return invalid;
	}
	public void setInvalid(String invalid) {
		this.invalid = invalid;
	}
	public String getPasswordTryTimes() {
		return passwordTryTimes;
	}
	public void setPasswordTryTimes(String passwordTryTimes) {
		this.passwordTryTimes = passwordTryTimes;
	}
	public Date getLastLoggedDate() {
		return lastLoggedDate;
	}
	public void setLastLoggedDate(Date lastLoggedDate) {
		this.lastLoggedDate = lastLoggedDate;
	}
	
	
	
	

}
