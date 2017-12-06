package com.yinzhiwu.yiwu.entity;

import java.math.BigDecimal;
import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw.PerformanceType;

@Entity
@Table(name="yiwu_operating_plan", 
		uniqueConstraints=@UniqueConstraint(name="uk_operatingPlan_storeidMonthType",
			columnNames={"store_id","month","type"}))
public class OperatingPlan extends BaseEntity{

	private static final long serialVersionUID = 2960628968069877610L;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(value=ConstraintMode.NO_CONSTRAINT))
	private DepartmentYzw store;
	
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date month;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length=32)
	private PerformanceType type;
	
	@Min(0)
	private BigDecimal amount;

	
	
	@Override
	public void init() {
		super.init();
		if(null==amount)
			amount = BigDecimal.valueOf(0);
		//设置month为每月1日
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.month);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		this.month = calendar.getTime();
	}

	public DepartmentYzw getStore() {
		return store;
	}


	public PerformanceType getType() {
		return type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setStore(DepartmentYzw store) {
		this.store = store;
	}


	public void setType(PerformanceType type) {
		this.type = type;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}
	
	
}
