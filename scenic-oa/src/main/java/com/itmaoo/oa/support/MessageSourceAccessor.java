package com.itmaoo.oa.support;

import org.springframework.context.MessageSource;

public class MessageSourceAccessor {

	MessageSource messageSource;

	public MessageSourceAccessor(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getMessage(String key, Object... params) {
		return messageSource.getMessage(key, params, null);
	}

}
