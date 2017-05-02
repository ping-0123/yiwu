package com.yinzhiwu.springmvc3.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("OtherTweet")
public class OtherTweet extends Tweet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5387727294279867071L;

}
