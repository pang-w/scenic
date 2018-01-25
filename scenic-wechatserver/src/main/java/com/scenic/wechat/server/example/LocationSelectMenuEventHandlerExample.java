package com.scenic.wechat.server.example;

import com.scenic.wechat.server.MessageProcessor;
import com.scenic.wechat.server.constant.WechatConstant;
import com.scenic.wechat.server.enums.EventType;
import com.scenic.wechat.server.enums.MessageType;
import com.scenic.wechat.server.handler.AbstractMessageHandler;
import com.scenic.wechat.server.message.request.BaseRequestMessage;
import com.scenic.wechat.server.message.request.LocationSelectMenuEventRequestMessage;
import com.scenic.wechat.server.message.response.BaseResponseMessage;
import com.scenic.wechat.server.utils.MessageUtils;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 发送位置菜单事件示例代码
 * author:980463316@qq.com
 * Created on 2016-09-07 23:37.
 */
@Slf4j
@Component
@MessageProcessor(messageType = MessageType.EVENT, eventType = EventType.EVENT_LOCATION_SELECT)
public class LocationSelectMenuEventHandlerExample extends AbstractMessageHandler {
	private Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {

        LocationSelectMenuEventRequestMessage locationSelectMenuEventRequestMessage = (LocationSelectMenuEventRequestMessage) baseRequestMessage;

        String eventKey = locationSelectMenuEventRequestMessage.getEventKey();

        //用户点击了发送位置菜单
        if (WechatConstant.MENU_LOCATION_SELECT_KEY.equalsIgnoreCase(eventKey)) {
            if (log.isInfoEnabled()) {
            	log.info("{} 发送了一个位置给我", locationSelectMenuEventRequestMessage.getFromUserName());
            }
            return MessageUtils.buildTextResponseMessage(baseRequestMessage, "您点击了[发送位置按钮]");
        }

        return null;

    }
}
