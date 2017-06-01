package com.yinzhiwu.springmvc3.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="yiwu_type")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_class", length=32)
@DiscriminatorValue(value="abstractType")
public class BaseType extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7200613894589888689L;
	
	@Column(length=32)
	private String name;
	
	

	public BaseType() {
		super();
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public BaseType(String name) {
		super();
		this.name = name;
	}
	
	

}
