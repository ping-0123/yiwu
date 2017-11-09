package com.yinzhiwu.yiwu.entity.income;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.yinzhiwu.yiwu.entity.BaseEntity;
import com.yinzhiwu.yiwu.enums.Relation;
import com.yinzhiwu.yiwu.enums.IncomeType;
import com.yinzhiwu.yiwu.event.IncomeEventType;

@SuppressWarnings("serial")
@Entity
@Table(name = "yiwu_income_factor")
public class IncomeFactor extends BaseEntity {

	@Column(columnDefinition="boolean not null default false")
	private Boolean removed;

	@Column(length=64)
	@Enumerated(EnumType.STRING)
	private IncomeEventType eventType;

	@Column(length=32)
	@Enumerated(EnumType.STRING)
	private IncomeType incomeType;

	@Column(length=32)
	@Enumerated(EnumType.STRING)
	private Relation relation;

	private Float factor;

	@Override
	public void init() {
		super.init();
		removed=false;
		if(null == factor)
			factor =0f;
	}

	public Boolean getRemoved() {
		return removed;
	}

	public void setRemoved(Boolean removed) {
		this.removed = removed;
	}

	public IncomeEventType getEventType() {
		return eventType;
	}

	public void setEventType(IncomeEventType eventType) {
		this.eventType = eventType;
	}

	public com.yinzhiwu.yiwu.enums.IncomeType getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(com.yinzhiwu.yiwu.enums.IncomeType incomeType) {
		this.incomeType = incomeType;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public Float getFactor() {
		return factor;
	}

	public void setFactor(Float factor) {
		this.factor = factor;
	}

	
	
}
