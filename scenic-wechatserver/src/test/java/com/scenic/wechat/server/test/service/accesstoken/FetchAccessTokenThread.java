package com.scenic.wechat.server.test.service.accesstoken;

import java.util.concurrent.CountDownLatch;

import com.scenic.wechat.server.bean.AccessTokenBean;
import com.scenic.wechat.server.service.accesstoken.AccessTokenService;

/**
 * 封装多线程获取access token
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-09-01 15:29.
 */
public class FetchAccessTokenThread implements Runnable {

    private String threadName;
    private AccessTokenService accessTokenService;
    private CountDownLatch latch;

    public FetchAccessTokenThread(String threadName, AccessTokenService accessTokenService, CountDownLatch latch) {
        this.threadName = threadName;
        this.accessTokenService = accessTokenService;
        this.latch = latch;
    }

    @Override
    public void run() {
        AccessTokenBean accessToken = accessTokenService.getAccessToken();
        System.out.println("thread name :" + threadName + " , token : " + accessToken);
        latch.countDown();
    }
}
