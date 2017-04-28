package com.yinzhiwu.springmvc3.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Brokerage")
public class BrokerageRecord extends MoneyRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4812339180959865837L;

}
