package com.scenic.wechat.server.message.response;

import lombok.Data;

/**
 * 回复语音消息的封装
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
public class VoiceResponseMessage extends BaseResponseMessage {

    private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}

}
