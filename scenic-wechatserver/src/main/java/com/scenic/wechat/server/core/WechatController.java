package com.scenic.wechat.server.core;

import com.scenic.wechat.server.config.WechatConfig;
import com.scenic.wechat.server.converter.MessageConvert;
import com.scenic.wechat.server.exception.NoMessageHandlerFoundException;
import com.scenic.wechat.server.handler.AbstractMessageHandler;
import com.scenic.wechat.server.message.request.BaseRequestMessage;
import com.scenic.wechat.server.message.response.BaseResponseMessage;
import com.scenic.wechat.server.utils.MessageUtils;
import com.scenic.wechat.server.utils.SignUtil;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 微信消息处理器的入口 <br/>
 * author : 980463316@qq.com <br/>
 * Created on 2016/8/5 16:29
 */
@Slf4j
@Controller
@RequestMapping(value = "/wechat")
public class WechatController {

    @Autowired
    private MessageDispatcher messageDispatcher;

    @Autowired
    private MessageConvert messageConverter;

    @Autowired
    private WechatConfig wechatConfig;
    
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 接收微信服务器的get请求
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public String doGet(@RequestParam(value = "signature") String signature,
                        @RequestParam(value = "timestamp") String timestamp,
                        @RequestParam(value = "nonce") String nonce,
                        @RequestParam(value = "echostr") String echostr) {

        if (SignUtil.checkSignature(wechatConfig.getToken(), signature, timestamp, nonce)) {
            return echostr;
        }

        return "";
    }

    /**
     * 接收微信服务器的post请求并响应
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, produces = "text/xml;charset=utf-8")
    public String doPost(HttpServletRequest request, HttpServletResponse response) {
        ServletInputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            Map<String, String> map = MessageUtils.parseRequest(inputStream);
//            map.forEach((key, value) -> {
//                System.out.println(String.format("key:%s,value:%s", key, value));
//            });
            String msgType = map.get("MsgType");
            String eventType = map.get("Event");
            //将用户发过来的消息转换成消息对象
            BaseRequestMessage requestMessage = messageConverter.doConvert(map);
            //将不同类型的消息发送给不同的消息处理器
            AbstractMessageHandler messageHandler = messageDispatcher.doDispatch(msgType, eventType);
            //调用消息处理器处理消息
            if (messageHandler == null) {
                throw new NoMessageHandlerFoundException("no message handler found for message type " + msgType + " and event type " + eventType);
            }
            BaseResponseMessage responseMessage = messageHandler.handleMessage(requestMessage);
            if (responseMessage == null) {
                return "";
            }
            //构造给用户的响应消息
            String responseXml = MessageUtils.messageToXml(responseMessage);
            if (log.isDebugEnabled()) {
                log.debug("response xml : {}", responseXml);
            }
            return responseXml;
        } catch (NoMessageHandlerFoundException e) {
            log.error(e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

}
