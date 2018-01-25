package com.scenic.wechat.server.example;

import com.scenic.wechat.server.MessageProcessor;
import com.scenic.wechat.server.enums.EventType;
import com.scenic.wechat.server.enums.MessageType;
import com.scenic.wechat.server.handler.AbstractMessageHandler;
import com.scenic.wechat.server.message.request.BaseRequestMessage;
import com.scenic.wechat.server.message.request.SubOrUnSubEventRequestMessage;
import com.scenic.wechat.server.message.response.BaseResponseMessage;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 取消关注事件处理器示例代码
 * author:980463316@qq.com
 * Created on 2016-09-07 23:11.
 */
@Slf4j
@Component
@MessageProcessor(messageType = MessageType.EVENT, eventType = EventType.EVENT_UNSUBSCRIBE)
public class UnSubscribeEventHandlerExample extends AbstractMessageHandler {
	private Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        SubOrUnSubEventRequestMessage unSubEventRequestMessage = (SubOrUnSubEventRequestMessage) baseRequestMessage;
        String fromUserName = unSubEventRequestMessage.getFromUserName();
        if (log.isWarnEnabled()) {
            log.warn("用户：[{}] 取消了对公众号的关注！", fromUserName);
        }
        //取消关注时清空微信用户的笑话浏览记录
        return null;
    }
}
