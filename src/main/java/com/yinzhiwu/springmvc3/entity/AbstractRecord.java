package com.yinzhiwu.springmvc3.entity;

import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractRecord extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8102222572160379415L;
	
	@ManyToOne(targetEntity=Distrituter.class)
	private Distrituter beneficiaty;

	@ManyToOne
	private RecordType recordType;
	
	private float income;
	
	private Distrituter contributor;
	
	private float contributedValue;
	
	private float incomeFactor;
	
	private Date contributedDate;

	public Distrituter getBeneficiaty() {
		return beneficiaty;
	}

	public void setBeneficiaty(Distrituter beneficiaty) {
		this.beneficiaty = beneficiaty;
	}

	public RecordType getRecordType() {
		return recordType;
	}

	public void setRecordType(RecordType recordType) {
		this.recordType = recordType;
	}

	public float getIncome() {
		return income;
	}

	public void setIncome(float income) {
		this.income = income;
	}

	public Distrituter getContributor() {
		return contributor;
	}

	public void setContributor(Distrituter contributor) {
		this.contributor = contributor;
	}

	public float getContributedValue() {
		return contributedValue;
	}

	public void setContributedValue(float contributedValue) {
		this.contributedValue = contributedValue;
	}

	public Date getContributedDate() {
		return contributedDate;
	}

	public void setContributedDate(Date contributedDate) {
		this.contributedDate = contributedDate;
	}

	public AbstractRecord() {
		super();
	}

	public float getIncomeFactor() {
		return incomeFactor;
	}

	public void setIncomeFactor(float incomeFactor) {
		this.incomeFactor = incomeFactor;
	}
	
	
	
}
