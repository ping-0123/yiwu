package com.yinzhiwu.yiwu.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PerformanceTweet")
public class PerformanceTweet extends Tweet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7246080944661258446L;

}
