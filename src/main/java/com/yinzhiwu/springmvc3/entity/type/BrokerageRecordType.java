package com.yinzhiwu.springmvc3.entity.type;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BrokerageRecordType")
public class BrokerageRecordType extends MoneyRecordType{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8601165666620370944L;

	public BrokerageRecordType() {
		super();
	}

	public BrokerageRecordType(String name, String comments, float factor) {
		super(name, comments, factor);
	}
	
	
}
