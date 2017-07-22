package com.yinzhiwu.yiwu.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AuditTweet")
public class AuditTweet extends Tweet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4910024393687456175L;

}
