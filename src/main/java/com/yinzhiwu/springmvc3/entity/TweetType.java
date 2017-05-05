package com.yinzhiwu.springmvc3.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="TweetType")
public class TweetType extends BaseType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9156582748305430473L;

	public TweetType() {
		super();
	}

	public TweetType(String name) {
		super(name);
	}
	
	

}
