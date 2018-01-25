package com.scenic.wechat.server.message.response;

import lombok.Data;

/**
 * 回复语音消息的封装
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
public class MusicResponseMessage extends BaseResponseMessage {

    private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}

}
