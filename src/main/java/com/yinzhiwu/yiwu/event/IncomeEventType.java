package com.yinzhiwu.yiwu.event;

/**
 * 
 * @author ping
 * @Date 2017年10月29日 下午5:16:03
 *
 */
public enum IncomeEventType {
	
	REGISTER_WITHOUT_INVATATION_CODE(10003),
	REGISTER_WITH_INVATATION_CODE(10004),
	SHARE_TWEET_BY_WECHAT_FIRST_THREE_TIMES_PER_DAY(10005),
	SHARE_TWEET_BY_WECHAT_AFTER_THREE_TIMES_PER_DAY(10006),
	PURCHASE_PRODUCTS(10007),
	PAY_DEPOSIT_BY_FUNDS(10008),
	PAY_DEPOSIT_BY_BROKERAGE(10009),
	YIELD_INTEREST(10010),
	WITHDRAW(10011),
	LESSON_APPOINTMENT(10027),
	CANCEL_LESSON_APPOINTMENT(10030),
	BREAK_LESSON_APPOINTMENT(10031),
	CHECK_IN_AFTER_APPOINTMENT(10028),
	CHECK_IN_WITHOUT_APPOINTMENT(10029);
	
	private final int id;

	public int getId() {
		return id;
	}

	private IncomeEventType(int id) {
		this.id = id;
	}
	
	
}
