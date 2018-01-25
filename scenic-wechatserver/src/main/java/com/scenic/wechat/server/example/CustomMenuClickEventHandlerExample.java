package com.scenic.wechat.server.example;

import com.scenic.wechat.server.MessageProcessor;
import com.scenic.wechat.server.bean.GetUserInfoParameter;
import com.scenic.wechat.server.bean.WechatUser;
import com.scenic.wechat.server.constant.WechatConstant;
import com.scenic.wechat.server.enums.EventType;
import com.scenic.wechat.server.enums.Lang;
import com.scenic.wechat.server.enums.MessageType;
import com.scenic.wechat.server.handler.AbstractMessageHandler;
import com.scenic.wechat.server.message.request.BaseRequestMessage;
import com.scenic.wechat.server.message.request.CustomMenuClickEventRequestMessage;
import com.scenic.wechat.server.message.response.BaseResponseMessage;
import com.scenic.wechat.server.service.user.WechatUserService;
import com.scenic.wechat.server.utils.MessageUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义菜单跳转事件示例代码
 * author:980463316@qq.com
 * Created on 2016-09-07 23:37.
 */
@Slf4j
@Component
@MessageProcessor(messageType = MessageType.EVENT, eventType = EventType.EVENT_CUSTOM_MENU_CLICK)
public class CustomMenuClickEventHandlerExample extends AbstractMessageHandler {

    @Autowired
    private WechatUserService wechatUserService;

    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {

        CustomMenuClickEventRequestMessage customMenuClickEventRequestMessage = (CustomMenuClickEventRequestMessage) baseRequestMessage;

        String eventKey = customMenuClickEventRequestMessage.getEventKey();

        if (WechatConstant.MENU_MY_CLICK_KEY.equalsIgnoreCase(eventKey)) {
            //用户点击了"我的信息"按钮
            WechatUser userInfo = wechatUserService.getWechatUserInfo(new GetUserInfoParameter(customMenuClickEventRequestMessage.getFromUserName(), Lang.CHINESE.getLanguageCode()));
            if (userInfo == null) {
                return MessageUtils.buildTextResponseMessage(baseRequestMessage, "抱歉,没有获取到您的信息,请您稍后再重试.");
            }
            String userInfoTemplate = "您的信息如下:\n☕openid:%s\n☕用户昵称:%s\n☕性别:%s\n☕所在国家:%s\n☕所在省份\n☕所在城市:%s";
            String userInfoString = String.format(userInfoTemplate, userInfo.getOpenid(), userInfo.getNickname(), userInfo.getSexString(), userInfo.getCountry(), userInfo.getProvince(), userInfo.getCity());
            return MessageUtils.buildTextResponseMessage(baseRequestMessage, userInfoString);
        }

        return null;

    }
}
