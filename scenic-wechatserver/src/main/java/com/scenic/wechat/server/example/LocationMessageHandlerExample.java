package com.scenic.wechat.server.example;

import com.scenic.wechat.server.MessageProcessor;
import com.scenic.wechat.server.enums.MessageType;
import com.scenic.wechat.server.handler.AbstractMessageHandler;
import com.scenic.wechat.server.message.request.BaseRequestMessage;
import com.scenic.wechat.server.message.request.LocationRequestMessage;
import com.scenic.wechat.server.message.response.BaseResponseMessage;
import com.scenic.wechat.server.utils.MessageUtils;

import org.springframework.stereotype.Component;

/**
 * 地理位置消息接收 的 code example
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-19 13:07.
 */
@Component
@MessageProcessor(messageType = MessageType.LOCATION_MESSAGE)
public class LocationMessageHandlerExample extends AbstractMessageHandler {
    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        //在这里实现你自己的业务逻辑
        LocationRequestMessage locationRequestMessage = (LocationRequestMessage) baseRequestMessage;
        String content = "您发送的地理位置消息如下：\nlabel:%s,\nlocation_x:%s,\nlocation_y:%s,\nscale:%s ";
        content = String.format(content,locationRequestMessage.getLabel(),locationRequestMessage.getLocation_X(),locationRequestMessage.getLocation_Y(),locationRequestMessage.getScale());
        return MessageUtils.buildTextResponseMessage(baseRequestMessage,content);
    }
}
