package com.yinzhiwu.springmvc3.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.yinzhiwu.springmvc3.entity.type.RecordType;

@Entity
@DiscriminatorValue("BrokerageRecord")
public class BrokerageRecord extends MoneyRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4812339180959865837L;

	public BrokerageRecord() {
		super();
	}

	public BrokerageRecord(Distributer beneficiaty, Distributer contributor, float contributedValue,
			RecordType recordType) {
		super(beneficiaty, contributor, contributedValue, recordType);
		super.setCurrentBrokerage(getCurrentBrokerage() + getIncome());
	}

	
}
