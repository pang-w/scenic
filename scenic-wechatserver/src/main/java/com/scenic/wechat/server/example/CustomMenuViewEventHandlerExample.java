package com.scenic.wechat.server.example;

import com.scenic.wechat.server.MessageProcessor;
import com.scenic.wechat.server.enums.EventType;
import com.scenic.wechat.server.enums.MessageType;
import com.scenic.wechat.server.handler.AbstractMessageHandler;
import com.scenic.wechat.server.message.request.BaseRequestMessage;
import com.scenic.wechat.server.message.request.CustomMenuClickEventRequestMessage;
import com.scenic.wechat.server.message.response.BaseResponseMessage;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 自定义菜单查看事件示例代码
 * author:980463316@qq.com
 * Created on 2016-09-07 23:37.
 */
@Slf4j
@Component
@MessageProcessor(messageType = MessageType.EVENT, eventType = EventType.EVENT_CUSTOM_MENU_VIEW)
public class CustomMenuViewEventHandlerExample extends AbstractMessageHandler {
	private Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        CustomMenuClickEventRequestMessage customMenuClickEventRequestMessage = (CustomMenuClickEventRequestMessage) baseRequestMessage;

        //在这里实现你自己的业务逻辑
        log.info("{} 点击了[view]类型的菜单,eventKey={}", customMenuClickEventRequestMessage.getFromUserName(), customMenuClickEventRequestMessage.getEventKey());
        return null;

    }
}
