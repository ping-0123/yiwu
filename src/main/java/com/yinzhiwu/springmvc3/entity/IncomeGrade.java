package com.yinzhiwu.springmvc3.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.yinzhiwu.springmvc3.entity.type.IncomeType;


@Entity
@Table(name="yiwu_income_grade",uniqueConstraints={
		@UniqueConstraint(name="uk_incomeGrade_gradeNo", columnNames="gradeNo"),
		@UniqueConstraint(name="uk_incomeGrade_name", columnNames="name")
})
public class IncomeGrade extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6432236461203601711L;

	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_incomeGrade_incomeType_id"))
	private IncomeType incomeType;
	
	@Column(nullable=false)  //uk
	private int gradeNo;
	
	@Column(length=32, nullable=false)  //uk
	private String name;
	
	private float upgradeExp;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="next_grade_id", foreignKey=@ForeignKey(name="fk_incomeGrade_nextGrade_id"))
	private IncomeGrade nextGrade;

	private Float increaseDiscount;
	
	private Boolean lowestGrade;
	
	private Boolean highesGrade;
	
	
	
	public IncomeGrade() {
		super();
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getUpgradeExp() {
		return upgradeExp;
	}

	public void setUpgradeExp(float upgradeExp) {
		this.upgradeExp = upgradeExp;
	}

	public IncomeGrade getNextGrade() {
		return nextGrade;
	}

	public void setNextGrade(IncomeGrade nextGrade) {
		this.nextGrade = nextGrade;
	}

	


	public int getGradeNo() {
		return gradeNo;
	}


	public Boolean getLowestGrade() {
		return lowestGrade;
	}


	public Boolean getHighesGrade() {
		return highesGrade;
	}


	public void setGradeNo(int gradeNo) {
		this.gradeNo = gradeNo;
	}


	public void setLowestGrade(Boolean lowestGrade) {
		this.lowestGrade = lowestGrade;
	}


	public void setHighesGrade(Boolean highesGrade) {
		this.highesGrade = highesGrade;
	}



	public IncomeType getIncomeType() {
		return incomeType;
	}



	public Float getIncreaseDiscount() {
		return increaseDiscount;
	}



	public void setIncomeType(IncomeType incomeType) {
		incomeType = incomeType;
	}



	public void setIncreaseDiscount(Float increaseDiscount) {
		this.increaseDiscount = increaseDiscount;
	}





	
	
}
