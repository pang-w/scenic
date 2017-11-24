package com.itmaoo.oa.config;

import org.springframework.beans.factory.annotation.Value;

//@Component
public class SysConfig {

	@Value("${sysCode}")
	private String sysCode;
	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

}
