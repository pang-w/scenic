package com.scenic.wechat.server.example;

import com.scenic.wechat.server.MessageProcessor;
import com.scenic.wechat.server.constant.WechatConstant;
import com.scenic.wechat.server.enums.EventType;
import com.scenic.wechat.server.enums.MessageType;
import com.scenic.wechat.server.handler.AbstractMessageHandler;
import com.scenic.wechat.server.message.request.BaseRequestMessage;
import com.scenic.wechat.server.message.request.ScanCodeEventRequestMessage;
import com.scenic.wechat.server.message.response.BaseResponseMessage;
import com.scenic.wechat.server.utils.MessageUtils;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 扫码带提示事件处理器
 * Author: jonny
 * Time: 2017-08-18 14:48.
 */
@Slf4j
@Component
@MessageProcessor(messageType = MessageType.EVENT, eventType = EventType.EVENT_SCAN_CODE_WAIT_MSG)
public class ScanCodeEventHandlerExample extends AbstractMessageHandler {
	private Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 真正的处理消息的方法
     *
     * @param baseRequestMessage
     * @return
     */
    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {

        ScanCodeEventRequestMessage scanCodeEventRequestMessage = (ScanCodeEventRequestMessage) baseRequestMessage;
        String eventKey = scanCodeEventRequestMessage.getEventKey();
        if (eventKey.equalsIgnoreCase(WechatConstant.MENU_SCAN_CODE_WAIT_MSG)) {
            if (log.isInfoEnabled()) {
                log.info("{} 扫描二维码的结果是: {}", scanCodeEventRequestMessage.getFromUserName(), scanCodeEventRequestMessage.getScanCodeInfo());
            }
            String template = "您的扫描结果是:%s";
            return MessageUtils.buildTextResponseMessage(baseRequestMessage, String.format(template, scanCodeEventRequestMessage.getScanCodeInfo().getScanResult()));
        }

        return null;
    }
}
