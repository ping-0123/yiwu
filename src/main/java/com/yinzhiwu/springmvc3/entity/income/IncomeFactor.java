package com.yinzhiwu.springmvc3.entity.income;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yinzhiwu.springmvc3.entity.BaseEntity;
import com.yinzhiwu.springmvc3.entity.type.EventType;
import com.yinzhiwu.springmvc3.entity.type.IncomeType;
import com.yinzhiwu.springmvc3.entity.type.RelationType;

@Entity
@Table(name="yiwu_income_factor")
public class IncomeFactor extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8480309517203173801L;

	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_incomeFactor_eventType_id"))
	private EventType eventType;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_incomeFactor_type_id"))
	private IncomeType incomeType;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_incomeFactor_relation_id"))
	private RelationType relation;
	
	private Float factor;

	public EventType getEventType() {
		return eventType;
	}


	public RelationType getRelation() {
		return relation;
	}

	public Float getFactor() {
		return factor;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}


	public void setRelation(RelationType relation) {
		this.relation = relation;
	}

	public void setFactor(Float factor) {
		this.factor = factor;
	}


	public IncomeType getIncomeType() {
		return incomeType;
	}


	public void setIncomeType(IncomeType type) {
		this.incomeType = type;
	}
	
	
}
