package com.yinzhiwu.yiwu.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NewsTweet")
public class NewsTweet extends Tweet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6222326868086500643L;

}
