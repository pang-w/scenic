package com.scenic.wechat.server.message.request;

import com.scenic.wechat.server.enums.MessageType;

import lombok.Data;

/**
 * Created by jonnyliu-ds8 on 2016/8/5.
 */
@Data
public class LinkRequestMessage extends BaseRequestMessage {

    /**
     *消息id，64位整型
     */
    private long MsgId;
    /**
     * 消息标题
     */
    private String Title;

    /**
     * 消息描述
     */
    private String Description;

    /**
     * 消息链接
     */
    private String Url;

    public long getMsgId() {
		return MsgId;
	}

	public void setMsgId(long msgId) {
		MsgId = msgId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	@Override
    public String getMsgType() {
        return MessageType.LINK_MESSAGE.getTypeStr();
    }

}
