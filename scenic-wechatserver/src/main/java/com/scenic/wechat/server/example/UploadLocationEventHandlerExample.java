package com.scenic.wechat.server.example;

import com.scenic.wechat.server.MessageProcessor;
import com.scenic.wechat.server.enums.EventType;
import com.scenic.wechat.server.enums.MessageType;
import com.scenic.wechat.server.handler.AbstractMessageHandler;
import com.scenic.wechat.server.message.request.BaseRequestMessage;
import com.scenic.wechat.server.message.request.UploadLocationEventRequestMessage;
import com.scenic.wechat.server.message.response.BaseResponseMessage;

import org.springframework.stereotype.Component;

/**
 * 用户上传地理位置信息事件类型
 * author:980463316@qq.com
 * Created on 2016-09-07 23:21.
 */
@Component
@MessageProcessor(messageType = MessageType.EVENT, eventType = EventType.EVENT_UPLOAD_LOCATION)
public class UploadLocationEventHandlerExample extends AbstractMessageHandler {

    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        UploadLocationEventRequestMessage uploadLocationEventRequestMessage = (UploadLocationEventRequestMessage) baseRequestMessage;
        //在这里实现你自己的业务逻辑

        return null;
    }
}
