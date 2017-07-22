package com.yinzhiwu.yiwu.entity.type;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yinzhiwu.yiwu.entity.income.IncomeFactor;

@Entity
@DiscriminatorValue("EventType")
public class EventType  extends BaseType{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5049815767098788287L;
	
	public static final EventType REGISTER_WITHOUT_INVATATION_CODE				  = new EventType(10003,"REGISTER_WITHOUT_INVATATION_CODE");
	public static final EventType REGISTER_WITH_INVATATION_CODE 				  = new EventType(10004,"REGISTER_WITH_INVATATION_CODE");
	public static final EventType SHARE_TWEET_BY_WECHAT_FIRST_THREE_TIMES_PER_DAY = new EventType(10005,"SHARE_TWEET_BY_WECHAT_FIRST_THREE_TIMES_PER_DAY");
	public static final EventType SHARE_TWEET_BY_WECHAT_AFTER_THREE_TIMES_PER_DAY = new EventType(10006,"SHARE_TWEET_BY_WECHAT_AFTER_THREE_TIMES_PER_DAY");
	public static final EventType PURCHASE_PRODUCTS 							  = new EventType(10007,"PURCHASE_PRODUCTS");
	public static final EventType PAY_DEPOSIT_BY_FUNDS							  = new EventType(10008,"PAY_DEPOSIT_BY_FUNDS");
	public static final EventType PAY_DEPOSIT_BY_BROKERAGE						  = new EventType(10009,"PAY_DEPOSIT_BY_BROKERAGE");
	public static final EventType YIELD_INTEREST								  = new EventType(10010,"YIELD_INTEREST");
	public static final EventType WITHDRAW 										  = new EventType(10011,"WITHDRAW");
	public static final EventType APPOINTMENT									  = new EventType(10027, "APPOINTMENT");
	public static final EventType CHECK_IN_AFTER_APPOINTMENT					  = new EventType(10028, "CHECK_IN_AFTER_APPOINTMENT");
	public static final EventType CHECK_IN_WITHOUT_APPOINTMENT					  = new EventType(10029, "CHECK_IN_WITHOUT_APPOINTMENT");
	public static final EventType UN_APPOINTMENT								  = new EventType(10030, "UN_APPOINTMENT");


	
	@JsonIgnore
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

	public EventType(int i, String string) {
		super(i, string);
	}
	
	

}
