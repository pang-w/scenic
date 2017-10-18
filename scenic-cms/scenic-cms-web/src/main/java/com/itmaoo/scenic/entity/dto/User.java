package com.itmaoo.scenic.entity.dto;

import java.io.Serializable;

/**
 * 
 * @author mario
 *
 */
public class User implements Serializable{
	private static final long serialVersionUID = 6194632578192753293L;
	
	private String uname;
	private String password;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
