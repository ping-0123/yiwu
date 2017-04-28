package com.yinzhiwu.springmvc3.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Funds")
public class FundsRecord extends MoneyRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 664622895118472803L;

}
