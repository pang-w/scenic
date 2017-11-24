package com.itmaoo.oa.entity;


public class BizException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public static final int CLIENT = 0;
	public static final int BUSINESS = 1;
	
	protected int type;

	protected ErrorInfo errorInfo;
	
	public BizException(ErrorInfo errorInfo) {
		this.type = -1;
		this.errorInfo = errorInfo;
	}

	public BizException(String errorCode, String errorMessage) {
		this.type = -1;
		this.errorInfo = new ErrorInfo();
		this.errorInfo.setErrorCode(errorCode);
		this.errorInfo.setErrorMessage(errorMessage);
	}

	public BizException(int type, String errorCode, String errorMessage) {
		this.type = -1;
		this.type = type;
		this.errorInfo = new ErrorInfo();
		this.errorInfo.setErrorCode(errorCode);
		this.errorInfo.setErrorMessage(errorMessage);
	}
	
	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}
	
	@Override
	public String toString() {
		return this.errorInfo.toString();
	}
	
	@Override
	public String getMessage() {
		return this.errorInfo.toString();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
