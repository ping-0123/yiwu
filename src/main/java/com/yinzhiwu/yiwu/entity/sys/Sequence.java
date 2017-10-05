package com.yinzhiwu.yiwu.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="sys_sequence")
public class Sequence {
	
	public static final String EMPLOYEE_NUMBER = "EMPLOYEE_NUMBER";
	
	@Id
	@GeneratedValue(generator="sequenceIdGenerator")
	@GenericGenerator(name="sequenceIdGenerator", strategy="assigned")
	@Column(length=32)
	private String name;
	private Integer value;
	
	public Sequence(){};
	
	public Sequence(String sequenceName, int i) {
		this.name =sequenceName;
		this.value=i;
	}
	public void increase(){
		this.value++;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(Integer value) {
		this.value = value;
	}
	
	
}
