package com.yinzhiwu.yiwu.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ChildTweet")
public class ChildTweet extends Tweet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3386233965908863609L;

}
