package com.scenic.wechat.server.message.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户 ----->  公众号
 * 封装用户发送给公众号的消息,各个类型的消息可继承该类实现扩展
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
public class BaseRequestMessage implements Serializable {

    /**
     * 开发者微信号
     */
    protected String ToUserName;

    /**
     * 发送方帐号（一个OpenID）
     */
    protected String FromUserName;

    /**
     * 消息创建时间 （整型）
     */
    protected long CreateTime;

    /**
     * 消息类型
     */
    protected String MsgType;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

}
