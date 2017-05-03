package com.yinzhiwu.springmvc3.entity;

import java.util.Date;

import javax.persistence.ConstraintMode;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractRecord extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8102222572160379415L;
	
	@ManyToOne(targetEntity=Distributer.class)
	@JoinColumn(name="beneficiaty_id", foreignKey=@ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Distributer beneficiaty;

	@ManyToOne
	@JoinColumn(name="recordType_id",foreignKey=@ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private RecordType recordType;	
	
	private float income;
	
	@ManyToOne
	@JoinColumn(name="contributor_id", foreignKey=@ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Distributer contributor;
	
	private float contributedValue;
	
	private float incomeFactor;
	
	private Date contributedDate;

	
	
	
	public Distributer getBeneficiaty() {
		return beneficiaty;
	}

	public void setBeneficiaty(Distributer beneficiaty) {
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

	public Distributer getContributor() {
		return contributor;
	}

	public void setContributor(Distributer contributor) {
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
		this.contributedDate= super.getLastModifiedDate();
	}

	public float getIncomeFactor() {
		return incomeFactor;
	}

	public void setIncomeFactor(float incomeFactor) {
		this.incomeFactor = incomeFactor;
	}

	public AbstractRecord(Distributer beneficiaty,  Distributer contributor,
			float contributedValue,RecordType recordType) {
		super();
		this.contributedDate= super.getLastModifiedDate();
		this.beneficiaty = beneficiaty;
		this.recordType = recordType;
		this.contributor = contributor;
		this.contributedValue = contributedValue;
		this.incomeFactor=recordType.getFactor();
		this.income = this.contributedValue * this.incomeFactor;
	}
	
	
	
}
