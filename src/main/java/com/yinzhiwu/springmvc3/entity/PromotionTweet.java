package com.yinzhiwu.springmvc3.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PromotionTweet")
public class PromotionTweet extends Tweet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -772815023740051020L;

}
