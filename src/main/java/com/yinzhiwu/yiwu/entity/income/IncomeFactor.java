package com.yinzhiwu.yiwu.entity.income;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yinzhiwu.yiwu.entity.BaseEntity;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.entity.type.IncomeType;
import com.yinzhiwu.yiwu.entity.type.RelationType;

@Entity
@Table(name = "yiwu_income_factor")
public class IncomeFactor extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8480309517203173801L;

	@Column(name = "delete_flag")
	private Boolean deleteFlag = Boolean.FALSE;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_incomeFactor_eventType_id"))
	private EventType eventType;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_incomeFactor_type_id"))
	private IncomeType incomeType;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_incomeFactor_relation_id"))
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

	public IncomeFactor() {
	}

	public IncomeFactor(EventType eventType, IncomeType incomeType, RelationType relation, Float factor) {
		this.eventType = eventType;
		this.incomeType = incomeType;
		this.relation = relation;
		this.factor = factor;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}
