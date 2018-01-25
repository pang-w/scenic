package com.scenic.wechat.server.service;

import com.google.common.base.Preconditions;
import com.scenic.wechat.server.bean.AccessTokenBean;
import com.scenic.wechat.server.service.accesstoken.AccessTokenService;

/**
 * 所有微信接口都应该继承该类来获得AccessTokenService对象及检验accessToken是否有效的方法
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2017-02-16 16:26.
 */
public abstract class AbstractWechatService{

    protected abstract AccessTokenService getAccessTokenService();

    /**
     * 检查accesstoken是否有效
     */
    protected AccessTokenBean checkAccessToken(){
        AccessTokenBean accessToken = getAccessTokenService().getAccessToken();
        Preconditions.checkNotNull(accessToken,"access token is not allowed to be empty!");
        Preconditions.checkState(!getAccessTokenService().isAccessTokenExpired(),"access token has already expired!");
        return accessToken;
    }
}
