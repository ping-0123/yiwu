package com.yinzhiwu.springmvc3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Test extends BaseEntity{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -42867625417854827L;
	
	
	private String name;
	
	
	

	public Test() {
		super();
	}

	@Column(length=32)
	public  String getName() {
		return name;
	}

	public  void setName(String name) {
		this.name = name;
	}


	
	
}
