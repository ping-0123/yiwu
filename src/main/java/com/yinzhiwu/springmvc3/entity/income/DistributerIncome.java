package com.yinzhiwu.springmvc3.entity.income;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;

import com.yinzhiwu.springmvc3.entity.BaseEntity;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.type.IncomeType;

@Entity
@Table(name="yiwu_distributer_income", uniqueConstraints={
		@UniqueConstraint(name="cuk_dIncome_distributer_incomeType",columnNames={"distributer_id", "incomeType_id"})
})
public class DistributerIncome extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1220405621432715846L;

	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_distributerIncome_distributer_id"))
	private Distributer distributer;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_distributerIncome_incomeType_id"))
	private IncomeType incomeType;
	
	@Min(0)
	private Float income;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_dIncome_incomeGrade_id"))
	private IncomeGrade incomeGrade;
	
	private Float accumulativeIncome;

	public DistributerIncome(Distributer benificiary, IncomeType incomeType2, IncomeGrade incomeGrade) {
		init();
		this.distributer = benificiary;
		this.incomeType = incomeType2;
		this.incomeGrade = incomeGrade;
	}

	public  DistributerIncome(){}
	
	@Override
	public void init() {
		super.init();
		this.income = 0f;
		this.accumulativeIncome = 0f;
	}

	public Distributer getDistributer() {
		return distributer;
	}

	public IncomeType getIncomeType() {
		return incomeType;
	}

	public Float getIncome() {
		return income;
	}

	public IncomeGrade getIncomeGrade() {
		return incomeGrade;
	}

	public Float getAccumulativeIncome() {
		return accumulativeIncome;
	}

	public void setDistributer(Distributer distributer) {
		this.distributer = distributer;
	}

	public void setIncomeType(IncomeType incomeType) {
		this.incomeType = incomeType;
	}

	public void setIncome(Float income) {
		this.income = income;
	}

	public void setIncomeGrade(IncomeGrade incomeGrade) {
		this.incomeGrade = incomeGrade;
	}

	public void setAccumulativeIncome(Float accumulativeIncome) {
		this.accumulativeIncome = accumulativeIncome;
	}
}
