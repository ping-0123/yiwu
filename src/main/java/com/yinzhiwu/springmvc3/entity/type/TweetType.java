package com.yinzhiwu.springmvc3.entity.type;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="TweetType")
public class TweetType extends BaseType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9156582748305430473L;
	
	public static final TweetType PRODUCT 			= new TweetType("PRODUCT");
	public static final TweetType MARKET_ACTIVITY 	= new TweetType("MARKET_ACTIVITY");
	public static final TweetType PROMOTION 		= new TweetType("PROMOTION");
	public static final TweetType PERFORMACE 		= new TweetType("PERFORMACE");
	public static final TweetType NEWS 				= new TweetType("NEWS");
	public static final TweetType CHILDREN 			= new TweetType("CHILDREN");
	public static final TweetType ADULT 			= new TweetType("ADULT");
	public static final TweetType OTHER 			= new TweetType("OTHER");

	public TweetType() {
		super();
	}

	public TweetType(String name) {
		super(name);
	}
	
	

}
