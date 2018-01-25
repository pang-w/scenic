package com.scenic.wechat.server.message.request;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 扫码带提示事件消息
 * Author: jonny
 * Time: 2017-08-18 14:37.
 */
@Data
public class ScanCodeEventRequestMessage extends BaseRequestMessage {

    /**
     * 事件类型，scancode_waitmsg
     */
    private String Event;

    private String EventKey;

    private ScanCodeInfo ScanCodeInfo = new ScanCodeInfo();

    public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public ScanCodeInfo getScanCodeInfo() {
		return ScanCodeInfo;
	}

	public void setScanCodeInfo(ScanCodeInfo scanCodeInfo) {
		ScanCodeInfo = scanCodeInfo;
	}

	public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
