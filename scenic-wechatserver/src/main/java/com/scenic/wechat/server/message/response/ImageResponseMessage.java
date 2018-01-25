package com.scenic.wechat.server.message.response;

import lombok.Data;

/**
 * 回复图片消息的封装
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
public class ImageResponseMessage extends BaseResponseMessage {

    private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}

}
