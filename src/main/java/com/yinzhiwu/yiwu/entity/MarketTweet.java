package com.yinzhiwu.yiwu.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MarketTweet")
public class MarketTweet extends Tweet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}