package com.yinzhiwu.yiwu.entity.income;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.yinzhiwu.yiwu.entity.BaseEntity;
import com.yinzhiwu.yiwu.entity.type.IncomeType;


@Entity
@Table(name="yiwu_income_grade",uniqueConstraints={
//		@UniqueConstraint(name="uk_incomeGrade_gradeNo", columnNames="gradeNo"),
//		@UniqueConstraint(name="uk_incomeGrade_name", columnNames="name")
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
	private Integer gradeNo;
	
	@Column(length=32, nullable=false)  //uk
	private String name;
	
	private Float upgradeNeededValue;
	
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
		this.incomeType = incomeType;
	}



	public void setIncreaseDiscount(Float increaseDiscount) {
		this.increaseDiscount = increaseDiscount;
	}





	public void setGradeNo(Integer gradeNo) {
		this.gradeNo = gradeNo;
	}



	public Float getUpgradeNeededValue() {
		return upgradeNeededValue;
	}



	public void setUpgradeNeededValue(Float upgradeNeededValue) {
		this.upgradeNeededValue = upgradeNeededValue;
	}






	public IncomeGrade(IncomeType incomeType, Integer gradeNo, String name, Float upgradeNeededValue,
			IncomeGrade nextGrade, Float increaseDiscount, Boolean lowestGrade, Boolean highesGrade) {
		this.incomeType = incomeType;
		this.gradeNo = gradeNo;
		this.name = name;
		this.upgradeNeededValue = upgradeNeededValue;
		this.nextGrade = nextGrade;
		this.increaseDiscount = increaseDiscount;
		this.lowestGrade = lowestGrade;
		this.highesGrade = highesGrade;
	}







	
	
}
