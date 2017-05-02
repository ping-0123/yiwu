package com.yinzhiwu.springmvc3.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MoneyRecordType")
public class MoneyRecordType extends RecordType{

	/**
	 * 
	 */
	private static final long serialVersionUID = 709214523193395045L;

	
	public MoneyRecordType(){
		super();
	}
	
	public MoneyRecordType(String name, String comments, float factor){
		super();
		super.setName(name);
		super.setComments(comments);
		super.setFactor(factor);
	}
}
