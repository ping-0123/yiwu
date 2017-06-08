package com.yinzhiwu.springmvc3.entity.type;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FundsRecordType")
public class FundsRecordType extends MoneyRecordType{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8007066781983533312L;

	public FundsRecordType() {
		super();
	}

	public FundsRecordType(String name, String comments, float factor) {
		super(name, comments, factor);
	}
	
	
}
