package com.yinzhiwu.springmvc3.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ProductTweet")
public class ProductTweet extends Tweet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 479512707705212011L;

}
