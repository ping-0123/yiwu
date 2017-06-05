package com.yinzhiwu.springmvc3.entity;

import java.util.Date;

import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

//@Entity
//@Table(name="yiwu_income_record")
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="type", length=32)
//@DiscriminatorValue("AbstractRecord")
@MappedSuperclass
public abstract class AbstractRecord extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8102222572160379415L;
	
	@ManyToOne(targetEntity=Distributer.class)
	@JoinColumn(name="beneficiaty_id", foreignKey=@ForeignKey(
			value =ConstraintMode.NO_CONSTRAINT,name="fk_record_beneficiaty_id"))
	private Distributer beneficiaty;

	@ManyToOne
	@JoinColumn(name="recordType_id",foreignKey=@ForeignKey(
			value=ConstraintMode.NO_CONSTRAINT,name="fk_record_recordType_id"))
	private RecordType recordType;	
	
	private float income;
	
	@ManyToOne
	@JoinColumn(name="contributor_id", foreignKey=@ForeignKey(
			value=ConstraintMode.NO_CONSTRAINT, name="fk_record_contributor_id"))
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
