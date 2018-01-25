package com.scenic.wechat.server.message.request;

import com.scenic.wechat.server.enums.MessageType;

import lombok.Data;

/**
 * 文本消息的封装
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
public class TextRequestMessage extends BaseRequestMessage {

    /**
     *消息id，64位整型
     */
    private long MsgId;

    /**
     * 消息内容
     */
    private String Content;

    public long getMsgId() {
		return MsgId;
	}

	public void setMsgId(long msgId) {
		MsgId = msgId;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getMsgType() {
        return MessageType.TEXT_MESSAGE.getTypeStr();
    }

}
