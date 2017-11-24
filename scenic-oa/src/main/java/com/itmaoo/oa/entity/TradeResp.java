package com.itmaoo.oa.entity;


public class TradeResp extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS_CODE = "0000";
	/** 响应代码 */
	private String respCode;
	/** 响应内容 */
	private String respMsg;
	/** 三方交易状态 */
	private String chlStatus;

	public boolean successful() {
		return respCode.equals(SUCCESS_CODE);
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

	public String getChlStatus() {
		return chlStatus;
	}

	public void setChlStatus(String chlStatus) {
		this.chlStatus = chlStatus;
	}
}
