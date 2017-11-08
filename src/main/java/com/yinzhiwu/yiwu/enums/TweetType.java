package com.yinzhiwu.yiwu.enums;

/**
*@Author ping
*@Time  创建时间:2017年11月8日上午10:14:52
*
*/

public enum TweetType {
	PRODUCT(10018),
	MARKET_ACTIVITY(10020),
	PROMOTION(10021),
	PERFORMACE(10022),
	NEWS(10023),
	CHILDREN(10024),
	ADULT(10025),
	OTHER(10026);
	
	private final int id;

	private TweetType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	
	
}
