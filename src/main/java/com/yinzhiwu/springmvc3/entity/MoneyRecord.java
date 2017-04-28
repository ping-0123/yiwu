package com.yinzhiwu.springmvc3.entity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="brokerage_funds", length=32)
@DiscriminatorValue("Brokerage")
public class MoneyRecord extends AbstractRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5236507567771241210L;

	private float currentBrokerage;
	
	private float currentFunds;
	
	
	private Order order;

	public float getCurrentBrokerage() {
		return currentBrokerage;
	}

	public void setCurrentBrokerage(float currentBrokerage) {
		this.currentBrokerage = currentBrokerage;
	}

	public float getCurrentFunds() {
		return currentFunds;
	}

	public void setCurrentFunds(float currentFunds) {
		this.currentFunds = currentFunds;
	}


	@ManyToOne
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
