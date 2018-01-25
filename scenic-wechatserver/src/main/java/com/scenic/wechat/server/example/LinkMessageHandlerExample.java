package com.scenic.wechat.server.example;

import com.scenic.wechat.server.MessageProcessor;
import com.scenic.wechat.server.enums.MessageType;
import com.scenic.wechat.server.handler.AbstractMessageHandler;
import com.scenic.wechat.server.message.request.BaseRequestMessage;
import com.scenic.wechat.server.message.request.LinkRequestMessage;
import com.scenic.wechat.server.message.response.BaseResponseMessage;
import com.scenic.wechat.server.utils.MessageUtils;

import org.springframework.stereotype.Component;

/**
 * 接收链接消息的code example
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-19 13:09.
 */
@Component
@MessageProcessor(messageType = MessageType.LINK_MESSAGE)
public class LinkMessageHandlerExample extends AbstractMessageHandler {
    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        //在这里实现你自己的业务逻辑
        LinkRequestMessage linkRequestMessage = (LinkRequestMessage) baseRequestMessage;
        String content = "您发送的链接消息如下：title:%s,url:%s,description:%s ";
        content = String.format(content,linkRequestMessage.getTitle(),linkRequestMessage.getUrl(),linkRequestMessage.getDescription());
        return MessageUtils.buildTextResponseMessage(baseRequestMessage,content);
    }
}
