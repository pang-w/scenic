package com.scenic.wechat.server.message.request;

import com.scenic.wechat.server.enums.MessageType;

import lombok.Data;

/**
 * 图片消息的封装
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
public class ImageRequestMessage extends BaseRequestMessage {

    /**
     *消息id，64位整型
     */
    private long MsgId;
    /**
     * 图片链接（由系统生成）
     */
    private String PicUrl;

    /**
     * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String MediaId;

    public long getMsgId() {
		return MsgId;
	}

	public void setMsgId(long msgId) {
		MsgId = msgId;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	@Override
    public String getMsgType() {
        return MessageType.IMAGE_MESSAGE.getTypeStr();
    }

}
