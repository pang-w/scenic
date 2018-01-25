package com.scenic.wechat.server.message.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Author: jonny
 * Time: 2017-08-18 18:01.
 */
@Data
public class ScanCodeInfo implements Serializable {

    private String ScanType;

    private String ScanResult;

	public String getScanType() {
		return ScanType;
	}

	public void setScanType(String scanType) {
		ScanType = scanType;
	}

	public String getScanResult() {
		return ScanResult;
	}

	public void setScanResult(String scanResult) {
		ScanResult = scanResult;
	}
    
}
