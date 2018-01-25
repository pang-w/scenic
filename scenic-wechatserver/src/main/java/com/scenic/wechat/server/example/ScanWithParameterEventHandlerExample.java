package com.scenic.wechat.server.example;

import com.scenic.wechat.server.MessageProcessor;
import com.scenic.wechat.server.enums.EventType;
import com.scenic.wechat.server.enums.MessageType;
import com.scenic.wechat.server.handler.AbstractMessageHandler;
import com.scenic.wechat.server.message.request.BaseRequestMessage;
import com.scenic.wechat.server.message.request.ScanQrWithParameterEventRequestMessage;
import com.scenic.wechat.server.message.response.BaseResponseMessage;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 带参数的二维码扫描事件示例代码
 * author:980463316@qq.com
 * Created on 2016-09-07 23:24.
 */
@Slf4j
@Component
@MessageProcessor(messageType = MessageType.EVENT, eventType = EventType.EVENT_SCAN)
public class ScanWithParameterEventHandlerExample extends AbstractMessageHandler {
	private Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        ScanQrWithParameterEventRequestMessage scanQrWithParameterEventRequestMessage = (ScanQrWithParameterEventRequestMessage) baseRequestMessage;
        if (log.isInfoEnabled()) {
            log.info("带参数的二维码扫描:" + scanQrWithParameterEventRequestMessage.toString());
        }
        return null;
    }
}
