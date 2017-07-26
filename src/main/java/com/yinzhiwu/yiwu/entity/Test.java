package com.yinzhiwu.yiwu.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;

//@Entity
//@Table
@Access(AccessType.PROPERTY)
public class Test extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -42867625417854827L;

	private String name;

	public Test() {
		super();
	}

	@Column(length = 32)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
