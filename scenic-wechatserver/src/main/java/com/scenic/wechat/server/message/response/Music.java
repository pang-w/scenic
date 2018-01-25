package com.scenic.wechat.server.message.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 单条音乐消息对象
 * Created by liujie on 2016/8/5 23:39.
 */
@Data
public class Music implements Serializable {

    /**
     * 音乐标题
     */
    private String Title;

    /**
     * 音乐链接
     */
    private String MusicURL;

    /**
     * 音乐描述
     */
    private String Description;

    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     */
    private String HQMusicUrl;


    /**
     * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     */
    private String ThumbMediaId;


	public String getTitle() {
		return Title;
	}


	public void setTitle(String title) {
		Title = title;
	}


	public String getMusicURL() {
		return MusicURL;
	}


	public void setMusicURL(String musicURL) {
		MusicURL = musicURL;
	}


	public String getDescription() {
		return Description;
	}


	public void setDescription(String description) {
		Description = description;
	}


	public String getHQMusicUrl() {
		return HQMusicUrl;
	}


	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}


	public String getThumbMediaId() {
		return ThumbMediaId;
	}


	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

}
