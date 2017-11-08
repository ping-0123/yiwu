package com.yinzhiwu.yiwu.entity.income;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.aspectj.weaver.AjAttribute.PrivilegedAttribute;

import com.yinzhiwu.yiwu.entity.BaseEntity;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.Message;
import com.yinzhiwu.yiwu.entity.type.IncomeType;
import com.yinzhiwu.yiwu.entity.type.RelationType;
import com.yinzhiwu.yiwu.enums.ContributerBenificiaryRelation;
import com.yinzhiwu.yiwu.event.IncomeEventType;

@SuppressWarnings("serial")
@Entity
@Table(name = "yiwu_income_record")
public class IncomeRecord extends BaseEntity {

	@Enumerated(EnumType.STRING)
	@Column(length=64)
	private IncomeEventType eventType;
	
	@Column(length=32)
	private String eventSourceClass;
	
	@Column(length=32)
	private String eventSourceId;

	@Enumerated(EnumType.STRING)
	@Column(length=32)
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

	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private ContributerBenificiaryRelation con_ben_relation;

	public IncomeRecord(IncomeEvent event2, IncomeFactor factor, Distributer benificiary) {
		super.init();
//		this.incomeEvent = event2;
//		this.incomeType = factor.getIncomeType();
		this.recordTimestamp = super.getCreateTime();
		this.contributor = event2.getDistributer();
		this.contributedValue = event2.getParam();
		this.benificiary = benificiary;
		this.incomeFactor = factor.getFactor();
		this.incomeValue = this.incomeFactor * this.contributedValue;
//		this.con_ben_relation = factor.getRelation();
	}

	public IncomeRecord() {
	}

	public Message generateMessage() {
		try {
//			return this.incomeEvent.generateMessage(this);
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public IncomeEventType getEventType() {
		return eventType;
	}

	public void setEventType(IncomeEventType eventType) {
		this.eventType = eventType;
	}

	public String getEventSourceClass() {
		return eventSourceClass;
	}

	public void setEventSourceClass(String eventSourceClass) {
		this.eventSourceClass = eventSourceClass;
	}

	public String getEventSourceId() {
		return eventSourceId;
	}

	public void setEventSourceId(String eventSourceId) {
		this.eventSourceId = eventSourceId;
	}

	public IncomeType getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(IncomeType incomeType) {
		this.incomeType = incomeType;
	}

	public Date getRecordTimestamp() {
		return recordTimestamp;
	}

	public void setRecordTimestamp(Date recordTimestamp) {
		this.recordTimestamp = recordTimestamp;
	}

	public Distributer getContributor() {
		return contributor;
	}

	public void setContributor(Distributer contributor) {
		this.contributor = contributor;
	}

	public Float getContributedValue() {
		return contributedValue;
	}

	public void setContributedValue(Float contributedValue) {
		this.contributedValue = contributedValue;
	}

	public Distributer getBenificiary() {
		return benificiary;
	}

	public void setBenificiary(Distributer benificiary) {
		this.benificiary = benificiary;
	}

	public Float getIncomeFactor() {
		return incomeFactor;
	}

	public void setIncomeFactor(Float incomeFactor) {
		this.incomeFactor = incomeFactor;
	}

	public Float getIncomeValue() {
		return incomeValue;
	}

	public void setIncomeValue(Float incomeValue) {
		this.incomeValue = incomeValue;
	}

	public Float getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Float currentValue) {
		this.currentValue = currentValue;
	}

	public ContributerBenificiaryRelation getCon_ben_relation() {
		return con_ben_relation;
	}

	public void setCon_ben_relation(ContributerBenificiaryRelation con_ben_relation) {
		this.con_ben_relation = con_ben_relation;
	}

	

}
