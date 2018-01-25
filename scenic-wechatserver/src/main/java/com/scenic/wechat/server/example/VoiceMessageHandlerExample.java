package com.scenic.wechat.server.example;

import com.scenic.wechat.server.MessageProcessor;
import com.scenic.wechat.server.enums.MessageType;
import com.scenic.wechat.server.handler.AbstractMessageHandler;
import com.scenic.wechat.server.message.request.BaseRequestMessage;
import com.scenic.wechat.server.message.request.VoiceRequestMessage;
import com.scenic.wechat.server.message.response.BaseResponseMessage;
import com.scenic.wechat.server.utils.MessageUtils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 语音消息接收和响应的code example
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-19 11:42.
 */
@Component
@MessageProcessor(messageType = MessageType.VOICE_MESSAGE)
public class VoiceMessageHandlerExample extends AbstractMessageHandler {
    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        //在这里实现你自己的业务逻辑
        VoiceRequestMessage voiceRequestMessage = (VoiceRequestMessage) baseRequestMessage;
        String recognition = voiceRequestMessage.getRecognition();
        String format = voiceRequestMessage.getFormat();
        String mediaId = voiceRequestMessage.getMediaId();

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("Recognition",recognition);
        paramMap.put("Format",format);
        paramMap.put("MediaId",mediaId);
        return MessageUtils.buildVoiceResponseMessage(baseRequestMessage,paramMap);
    }
}
