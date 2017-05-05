package com.yinzhiwu.springmvc3.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FundsRecord")
public class FundsRecord extends MoneyRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 664622895118472803L;

	public FundsRecord() {
		super();
	}

	public FundsRecord(Distributer beneficiaty, Distributer contributor, float contributedValue,
			RecordType recordType) {
		super(beneficiaty, contributor, contributedValue, recordType);
		super.setCurrentFunds(getIncome() + getCurrentFunds());
	}

	
}
