package com.itmaoo.oa.entity;

/**
 * 日志描述信息
 */
public class ActionInfo {

    /**
     * 操作关键字
     */
    private String keywords;
    /**
     * 处理描述
     */
    private String msg;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
