package com.scenic.wechat.server.message.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Author: jonny
 * Time: 2017-08-19 10:16.
 */
@Data
public class SendPicsInfo implements Serializable {
    /**
     * 发送图片的数量
     */
    private int Count;
    /**
     * 图片列表
     */
    private List<PhotoItem> PicList;
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	public List<PhotoItem> getPicList() {
		return PicList;
	}
	public void setPicList(List<PhotoItem> picList) {
		PicList = picList;
	}

}
