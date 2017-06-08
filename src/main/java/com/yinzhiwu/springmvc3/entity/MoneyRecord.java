package com.yinzhiwu.springmvc3.entity;

import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yinzhiwu.springmvc3.entity.type.RecordType;
import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;

@Entity
@Table(name="yiwu_money_record")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", length=32)
@DiscriminatorValue("MoneyRecord")
//@DiscriminatorValue("MoneyRecord")
public abstract class MoneyRecord extends AbstractRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5236507567771241210L;

	private float currentBrokerage;
	
	private float currentFunds;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="order_id", 
		foreignKey=@ForeignKey(value = ConstraintMode.NO_CONSTRAINT, name="fk_moneyrecord_order_id"))  //
	private OrderYzw order;

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


	public MoneyRecord(Distributer beneficiaty, Distributer contributor, float contributedValue,
			RecordType recordType) {
		super(beneficiaty, contributor, contributedValue, recordType);
		this.currentBrokerage=beneficiaty.getBrokerage();
		this.currentFunds = beneficiaty.getFunds();
	}

	public MoneyRecord() {
		super();
	}

	public OrderYzw getOrder() {
		return order;
	}

	public void setOrder(OrderYzw order) {
		this.order = order;
	}
	
	
}
