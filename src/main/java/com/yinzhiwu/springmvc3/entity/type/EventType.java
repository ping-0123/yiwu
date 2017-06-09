package com.yinzhiwu.springmvc3.entity.type;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.yinzhiwu.springmvc3.entity.IncomeFactor;

@Entity
@DiscriminatorValue("EventType")
public class EventType  extends BaseType{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5049815767098788287L;
	
	public static final EventType REGISTER_WITHOUT_INVATATION_CODE				  = new EventType("REGISTER_WITHOUT_INVATATION_CODE");
	public static final EventType REGISTER_WITH_INVATATION_CODE 				  = new EventType("REGISTER_WITH_INVATATION_CODE");
	public static final EventType SHARE_TWEET_BY_WECHAT_FIRST_THREE_TIMES_PER_DAY = new EventType("SHARE_TWEET_BY_WECHAT_FIRST_THREE_TIMES_PER_DAY");
	public static final EventType SHARE_TWEET_BY_WECHAT_AFTER_THREE_TIMES_PER_DAY = new EventType("SHARE_TWEET_BY_WECHAT_AFTER_THREE_TIMES_PER_DAY");
	public static final EventType PURCHASE_PRODUCTS 							  = new EventType("PURCHASE_PRODUCTS");
	public static final EventType PAY_DEPOSIT_BY_FUNDS							  = new EventType("PAY_DEPOSIT_BY_FUNDS");
	public static final EventType PAY_DEPOSIT_BY_BROKERAGE						  = new EventType("PAY_DEPOSIT_BY_BROKERAGE");
	public static final EventType YIELD_INTEREST								  = new EventType("YIELD_INTEREST");
	public static final EventType WITHDRAW 										  = new EventType("WITHDRAW");
	
	
	
	@OneToMany(mappedBy="eventType")
	private List<IncomeFactor> incomeFactors = new ArrayList<>();

	public List<IncomeFactor> getIncomeFactors() {
		return incomeFactors;
	}

	public void setIncomeFactors(List<IncomeFactor> incomeFactors) {
		this.incomeFactors = incomeFactors;
	}

	public EventType() {
		super();
	}

	public EventType(String name) {
		super(name);
	}
	
	

}
