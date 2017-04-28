package com.yinzhiwu.springmvc3.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class MoneyRecord extends AbstractRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5236507567771241210L;

	private float currentMoney;
	
	private float currentFunds;
	
	@ManyToOne
	private Order order;

	public float getCurrentMoney() {
		return currentMoney;
	}

	public void setCurrentMoney(float currentMoney) {
		this.currentMoney = currentMoney;
	}

	public float getCurrentFunds() {
		return currentFunds;
	}

	public void setCurrentFunds(float currentFunds) {
		this.currentFunds = currentFunds;
	}
	
	
}
