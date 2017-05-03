package com.yinzhiwu.springmvc3.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("WeChat")
public class WeChatShareTweet extends ShareTweet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -219687631656636689L;

}
