package com.scenic.wechat.server.message.response;

import lombok.Data;

/**
 * 回复语音消息的封装
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
public class VideoResponseMessage extends BaseResponseMessage {

    private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}

}
