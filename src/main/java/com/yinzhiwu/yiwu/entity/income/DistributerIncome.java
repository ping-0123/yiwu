package com.yinzhiwu.yiwu.entity.income;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.yinzhiwu.yiwu.entity.BaseEntity;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.enums.IncomeType;

@SuppressWarnings("serial")
@Entity
@Table(name = "yiwu_distributer_income", uniqueConstraints = {
		@UniqueConstraint(name = "cuk_dIncome_distributer_type", columnNames = { "distributer_id","type" }) 
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DistributerIncome extends BaseEntity {


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_distributerIncome_distributer_id"))
	private Distributer distributer;

	private IncomeType type;

	@Column(name="income")
	private Float value;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="incomeGrade_id",foreignKey = @ForeignKey(name = "fk_dIncome_incomeGrade_id"))
	private IncomeGrade grade;

	@Column(name="accumulativeIncome")
	private Float accumulativeValue;

	public DistributerIncome(Distributer benificiary, IncomeType type, IncomeGrade grade) {
		init();
		this.distributer = benificiary;
		this.type = type;
		this.grade = grade;
	}

	public DistributerIncome() {
	}

	@Override
	public void init() {
		super.init();
		if(null == value)
			this.value = 0f;
		if(null == accumulativeValue)
			this.accumulativeValue = 0f;
	}

	public Distributer getDistributer() {
		return distributer;
	}

	public IncomeType getType() {
		return type;
	}

	public Float getValue() {
		return value;
	}

	public IncomeGrade getGrade() {
		return grade;
	}

	public Float getAccumulativeValue() {
		return accumulativeValue;
	}

	public void setDistributer(Distributer distributer) {
		this.distributer = distributer;
	}

	public void setType(IncomeType type) {
		this.type = type;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public void setGrade(IncomeGrade grade) {
		this.grade = grade;
	}

	public void setAccumulativeValue(Float accumulativeValue) {
		this.accumulativeValue = accumulativeValue;
	}

	
}
