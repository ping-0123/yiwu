package com.yinzhiwu.yiwu.entity;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw.PerformanceType;

@Entity
@Table(name = "yiwu_plan_revenue", 
	uniqueConstraints = @UniqueConstraint(name = "uk_PlanRevenue_storeId_year_month_performanceType", 
	columnNames = {"store_id", "year", "month", "performanceType" }))
public class PlanRevenue extends BaseEntity{

	private static final long serialVersionUID = -5624395910196573287L;

	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(value=ConstraintMode.NO_CONSTRAINT))
	private DepartmentYzw store;

	@Column
	@NotNull
	private Integer year;

	@Column
	@Min(1)
	@Max(12)
	private Integer month;

	private PerformanceType performanceType;

	@Column
	private Double amount;

	public Integer getYear() {
		return year;
	}

	public Integer getMonth() {
		return month;
	}

	public PerformanceType getPerformanceType() {
		return performanceType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public void setPerformanceType(PerformanceType performanceType) {
		this.performanceType = performanceType;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public DepartmentYzw getStore() {
		return store;
	}

	public void setStore(DepartmentYzw store) {
		this.store = store;
	}
	
	
	
}
