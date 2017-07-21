package com.yinzhiwu.yiwu.entity.type;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="TweetType")
public class TweetType extends BaseType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9156582748305430473L;
	
	public static final TweetType PRODUCT 			= new TweetType(10018,"PRODUCT");
	public static final TweetType MARKET_ACTIVITY 	= new TweetType(10020,"MARKET_ACTIVITY");
	public static final TweetType PROMOTION 		= new TweetType(10021,"PROMOTION");
	public static final TweetType PERFORMACE 		= new TweetType(10022,"PERFORMACE");
	public static final TweetType NEWS 				= new TweetType(10023,"NEWS");
	public static final TweetType CHILDREN 			= new TweetType(10024,"CHILDREN");
	public static final TweetType ADULT 			= new TweetType(10025,"ADULT");
	public static final TweetType OTHER 			= new TweetType(10026,"OTHER");

	public TweetType() {
		super();
	}

	public TweetType(String name) {
		super(name);
	}

	public TweetType(int i, String string) {
		super(i, string);
	}
	
	

}
