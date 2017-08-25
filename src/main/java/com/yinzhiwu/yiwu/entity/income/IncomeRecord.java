package com.yinzhiwu.yiwu.entity.income;

import java.util.Date;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yinzhiwu.yiwu.entity.BaseEntity;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.Message;
import com.yinzhiwu.yiwu.entity.type.IncomeType;
import com.yinzhiwu.yiwu.entity.type.RelationType;

@Entity
@Table(name = "yiwu_income_record")
public class IncomeRecord extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4478053780652759400L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_incomeRecord_event_id", value=ConstraintMode.NO_CONSTRAINT))
	private IncomeEvent incomeEvent;

	@ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_incomeRecord_incomeType_id", value = ConstraintMode.NO_CONSTRAINT))
	private IncomeType incomeType;

	private Date recordTimestamp;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_incomeRecord_contributor_id", value = ConstraintMode.NO_CONSTRAINT))
	private Distributer contributor;

	private Float contributedValue;

	@ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_incomeRecord_benificiary_id", value=ConstraintMode.NO_CONSTRAINT))
	private Distributer benificiary;

	private Float incomeFactor;

	private Float incomeValue;

	private Float currentValue;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_incomeRecord_relationType_id", value = ConstraintMode.NO_CONSTRAINT))
	private RelationType con_ben_relation;

	public IncomeRecord(IncomeEvent event2, IncomeFactor factor, Distributer benificiary) {
		super.init();
		this.incomeEvent = event2;
		this.incomeType = factor.getIncomeType();
		this.recordTimestamp = super.getCreateDate();
		this.contributor = event2.getDistributer();
		this.contributedValue = event2.getParam();
		this.benificiary = benificiary;
		this.incomeFactor = factor.getFactor();
		this.incomeValue = this.incomeFactor * this.contributedValue;
		this.con_ben_relation = factor.getRelation();
	}

	public IncomeRecord() {
	}

	public Message generateMessage() {
		try {
			return this.incomeEvent.generateMessage(this);
		} catch (Exception e) {
			return null;
		}
	}

	public IncomeType getIncomeType() {
		return incomeType;
	}

	public Date getRecordTimestamp() {
		return recordTimestamp;
	}

	public Distributer getContributor() {
		return contributor;
	}

	public Float getContributedValue() {
		return contributedValue;
	}

	public Distributer getBenificiary() {
		return benificiary;
	}

	public Float getIncomeFactor() {
		return incomeFactor;
	}

	public Float getIncomeValue() {
		return incomeValue;
	}

	public RelationType getCon_ben_relation() {
		return con_ben_relation;
	}

	public void setIncomeType(IncomeType incomeType) {
		this.incomeType = incomeType;
	}

	public void setRecordTimestamp(Date recordTimestamp) {
		this.recordTimestamp = recordTimestamp;
	}

	public void setContributor(Distributer contributor) {
		this.contributor = contributor;
	}

	public void setContributedValue(Float contributedValue) {
		this.contributedValue = contributedValue;
	}

	public void setBenificiary(Distributer benificiary) {
		this.benificiary = benificiary;
	}

	public void setIncomeFactor(Float incomeFactor) {
		this.incomeFactor = incomeFactor;
	}

	public void setIncomeValue(Float incomeValue) {
		this.incomeValue = incomeValue;
	}

	public void setCon_ben_relation(RelationType con_ben_relation) {
		this.con_ben_relation = con_ben_relation;
	}

	public IncomeEvent getIncomeEvent() {
		return incomeEvent;
	}

	public void setIncomeEvent(IncomeEvent incomeEvent) {
		this.incomeEvent = incomeEvent;
	}

	public Float getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Float currentValue) {
		this.currentValue = currentValue;
	}

}
