package com.yinzhiwu.springmvc3.entity.income;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.type.EventType;

@Entity
@DiscriminatorValue("YieldInterestEvent")
public class YieldInterestEvent extends IncomeEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6827551515948934412L;

	public YieldInterestEvent() {
	}

	public YieldInterestEvent(Distributer distributer, EventType type, Float param) {
		super(distributer, type, param);
	}

	public YieldInterestEvent(Distributer distributer, Float param) {
		super(distributer, EventType.YIELD_INTEREST, param);
	}
	
}
