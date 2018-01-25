package com.scenic.wechat.server.example;

import com.scenic.wechat.server.MessageProcessor;
import com.scenic.wechat.server.enums.MessageType;
import com.scenic.wechat.server.handler.AbstractMessageHandler;
import com.scenic.wechat.server.message.request.BaseRequestMessage;
import com.scenic.wechat.server.message.request.ImageRequestMessage;
import com.scenic.wechat.server.message.response.BaseResponseMessage;
import com.scenic.wechat.server.utils.MessageUtils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 视频消息接收和响应的code example，
 * 实现回复相同的图片给用户
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-19 11:42.
 */
@Component
@MessageProcessor(messageType = MessageType.IMAGE_MESSAGE)
public class ImageMessageHandlerExample extends AbstractMessageHandler {
    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        //在这里实现你自己的业务逻辑
        ImageRequestMessage imageRequestMessage = (ImageRequestMessage) baseRequestMessage;
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("MediaId",imageRequestMessage.getMediaId());
        return MessageUtils.buildImageResponseMessage(baseRequestMessage,paramMap);
    }
}
