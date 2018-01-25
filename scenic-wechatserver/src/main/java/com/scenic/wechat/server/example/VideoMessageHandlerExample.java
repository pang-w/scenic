package com.scenic.wechat.server.example;

import com.scenic.wechat.server.MessageProcessor;
import com.scenic.wechat.server.enums.MessageType;
import com.scenic.wechat.server.handler.AbstractMessageHandler;
import com.scenic.wechat.server.message.request.BaseRequestMessage;
import com.scenic.wechat.server.message.request.VideoRequestMessage;
import com.scenic.wechat.server.message.response.BaseResponseMessage;
import com.scenic.wechat.server.utils.MessageUtils;

import org.springframework.stereotype.Component;

/**
 * 图片消息接收和响应的code example
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-19 11:42.
 */
@Component
@MessageProcessor(messageType = MessageType.VIDEO_MESSAGE)
public class VideoMessageHandlerExample extends AbstractMessageHandler {
    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        //在这里实现你自己的业务逻辑
        VideoRequestMessage videoRequestMessage = (VideoRequestMessage) baseRequestMessage;
        String mediaId = videoRequestMessage.getMediaId();
        String thumbMediaId = videoRequestMessage.getThumbMediaId();
        String content = "您发送的视频mediaId:%s \t,ThumbMediaId:%s ";
        content = String.format(content,mediaId,thumbMediaId);
        return MessageUtils.buildTextResponseMessage(baseRequestMessage,content);
    }
}
