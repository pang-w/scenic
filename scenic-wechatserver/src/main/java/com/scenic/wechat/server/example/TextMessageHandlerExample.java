package com.scenic.wechat.server.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.scenic.wechat.server.MessageProcessor;
import com.scenic.wechat.server.enums.MessageType;
import com.scenic.wechat.server.handler.AbstractMessageHandler;
import com.scenic.wechat.server.message.request.BaseRequestMessage;
import com.scenic.wechat.server.message.request.TextRequestMessage;
import com.scenic.wechat.server.message.response.Article;
import com.scenic.wechat.server.message.response.BaseResponseMessage;
import com.scenic.wechat.server.service.user.WechatUserService;
import com.scenic.wechat.server.utils.MessageUtils;

/**
 * 文本消息处理器helloworld示例
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-19 10:07.
 */
@Component
@MessageProcessor(messageType = MessageType.TEXT_MESSAGE)
public class TextMessageHandlerExample extends AbstractMessageHandler {
	private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WechatUserService wechatUserService;
    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        System.out.println(Thread.currentThread().getName());
        String url = "http://www.iukiss.com/action/wechat/search";
        TextRequestMessage textRequestMessage = (TextRequestMessage) baseRequestMessage;

    	JSONObject json = new JSONObject();
    	json.put("username", textRequestMessage.getContent());
    	Map<String,Object> data = doPost(url, json);
    	
        List<Article> list = assimbleArticles(data);
        if(list.isEmpty()){
        	json = new JSONObject();
        	json.put("title", textRequestMessage.getContent());
        	
        	data = doPost(url, json);
            list = assimbleArticles(data);
        }
       if(list.isEmpty()){
        	json = new JSONObject();
        	json.put("content", textRequestMessage.getContent());
        	
        	data = doPost(url, json);
            list = assimbleArticles(data);
        }
        if(list.isEmpty()){
        	return MessageUtils.buildTextResponseMessage(baseRequestMessage, "什么鬼，听不懂，说个关键字！");
        }
        Map<String, String> param = new HashMap<>();
        param.put("ArticleCount", String.valueOf(list.size()));
        return MessageUtils.buildNewsResponseMessage(baseRequestMessage, param, list);
    }
    @SuppressWarnings("unchecked")
    public static  List<Article> assimbleArticles(Map<String,Object> response){
    	List<Article> list = new ArrayList<>();
		Map<String,Object> data = (Map<String, Object>) response.get("data");
		if(data!=null){
			List<Map<String,Object>> dataList =(List<Map<String,Object>>) data.get("dataList");
			if(dataList!=null){
				for(Map<String, Object> articleMap:dataList){
		    		 Article article = new Article();
		    	        article.setUrl("http://www.iukiss.com/a/"+(String)articleMap.get("uuid"));
		    	        article.setTitle((String)articleMap.get("title"));
		    	        article.setPicUrl((String)articleMap.get("defaultImageUrl"));
		    	        article.setDescription((String)articleMap.get("description"));
		    	        list.add(article);
		    	}
			}
		}
    	
        return list;
    }
    public  Map<String,Object> doPost(String url,JSONObject json){
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        Map<String,Object> response = null;
        try {
            StringEntity s = new StringEntity(json.toString(),Consts.UTF_8);
            s.setContentEncoding(Consts.UTF_8.toString());
            s.setContentType("application/json;charset=utf-8");//发送json数据需要设置contentType
            
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.parseObject(result);
            }
        } catch (Exception e) {
        	log.error("网络请求失败"+url);
        	throw new RuntimeException(e);
            
        }
        return response;
    }

}
