package com.scenic.wechat.server.message.request;

import lombok.Data;

/**
 * 自定义菜单点击事件消息的封装
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
public class CustomMenuClickEventRequestMessage extends BaseRequestMessage {

    /**
     * 事件类型，
     * 值为VIEW时EventKey值为设置的跳转URL
     * 值为CLICK时,事件KEY值与自定义菜单接口中KEY值对应
     */
    protected String Event;
    /**
     * 值为VIEW时EventKey值为设置的跳转URL
     * 值为CLICK时,事件KEY值与自定义菜单接口中KEY值对应
     */
    protected String EventKey;
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

}
