package com.itmaoo.oa.entity;

public class ServiceMessage extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS_CODE = "0000";

	/** 发送系统代码 **/
	private String sysCode;
	/** 渠道编号，例如web渠道、app渠道等 **/
	private String srcNo;
	/** 终端号，用户使用的终端设备，如pos机标识号 **/
	private String termNo;
	/** 前置机编号（前置机机器名） **/
	private String frontNo;
	/** 前置机时间（精确到秒） **/
	private String frontTime;
	/** 前置交易流水号 **/
	private String frontTrdNum;
	/** 响应代码 **/
	private String respCode;
	/** 响应流水号 **/
	private String respTrdNum;
	/** 响应内容 **/
	private String respMsg;
	/** 响应机编号（响应机机器名） **/
	private String backNo;
	/** 响应机时间（精确到秒） **/
	private String backTime;
	/** 业务类型 **/
	private String bizType;


	public String getRespTrdNum() {
		return respTrdNum;
	}

	public void setRespTrdNum(String respTrdNum) {
		this.respTrdNum = respTrdNum;
	}

	public String getFrontTrdNum() {
		return frontTrdNum;
	}

	public void setFrontTrdNum(String frontTrdNum) {
		this.frontTrdNum = frontTrdNum;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public boolean successful() {
		return respCode.equals(SUCCESS_CODE);
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getSrcNo() {
		return srcNo;
	}

	public void setSrcNo(String srcNo) {
		this.srcNo = srcNo;
	}

	public String getTermNo() {
		return termNo;
	}

	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}

	public String getFrontNo() {
		return frontNo;
	}

	public void setFrontNo(String frontNo) {
		this.frontNo = frontNo;
	}

	public String getFrontTime() {
		return frontTime;
	}

	public void setFrontTime(String frontTime) {
		this.frontTime = frontTime;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public String getBackNo() {
		return backNo;
	}

	public void setBackNo(String backNo) {
		this.backNo = backNo;
	}

	public String getBackTime() {
		return backTime;
	}

	public void setBackTime(String backTime) {
		this.backTime = backTime;
	}

}
