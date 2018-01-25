package com.scenic.wechat.server.example;

import com.scenic.wechat.server.MessageProcessor;
import com.scenic.wechat.server.constant.WechatConstant;
import com.scenic.wechat.server.enums.EventType;
import com.scenic.wechat.server.enums.MessageType;
import com.scenic.wechat.server.handler.AbstractMessageHandler;
import com.scenic.wechat.server.message.request.BaseRequestMessage;
import com.scenic.wechat.server.message.request.PhotoMenuEventRequestMessage;
import com.scenic.wechat.server.message.response.BaseResponseMessage;
import com.scenic.wechat.server.utils.MessageUtils;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 接收微信相册发图菜单事件示例代码
 * author:980463316@qq.com
 * Created on 2016-09-07 23:37.
 */
@Slf4j
@Component
@MessageProcessor(messageType = MessageType.EVENT, eventType = EventType.EVENT_PIC_WEIXIN)
public class PhotoWeiXinMenuEventHandlerExample extends AbstractMessageHandler {
	private Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {

        PhotoMenuEventRequestMessage photoMenuEventRequestMessage = (PhotoMenuEventRequestMessage) baseRequestMessage;

        String eventKey = photoMenuEventRequestMessage.getEventKey();

        //用户点击了微信相册菜单
        if (WechatConstant.MENU_PIC_WEIXIN.equalsIgnoreCase(eventKey)) {
            if (log.isInfoEnabled()) {
                log.info("{} 点击了微信相册发图菜单", photoMenuEventRequestMessage.getFromUserName());
            }
            return MessageUtils.buildTextResponseMessage(baseRequestMessage, "您点击了[微信相册菜单]");
        }

        return null;

    }
}
