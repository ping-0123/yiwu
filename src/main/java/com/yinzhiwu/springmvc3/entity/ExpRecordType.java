package com.yinzhiwu.springmvc3.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("ExpRecordType")
public class ExpRecordType  extends RecordType{

	/**
	 * 
	 */
	private static final long serialVersionUID = -151542885025879460L;

	
	public ExpRecordType(){
		super();
	}
	
	public ExpRecordType(String name, float factor){
		super();
		setName(name);
		setFactor(factor);
	}
}
