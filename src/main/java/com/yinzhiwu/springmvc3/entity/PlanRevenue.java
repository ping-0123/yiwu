package com.yinzhiwu.springmvc3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.yinzhiwu.springmvc3.entity.type.ProductType;

@Entity
@Table(name="yiwu_plan_revenue", uniqueConstraints=
	@UniqueConstraint(name="uk_PlanRevenue_storeId_year_month_productTypeId", 
			columnNames={"storeId","year", "month", "productTypeId"}))
public class PlanRevenue {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	@NotNull
	private Integer storeId;
	
	@Column
	@NotNull
	private Integer year;
	
	@Column
	@Min(1)
	@Max(12)
	private Integer month;
	
	@ManyToOne
	@JoinColumn(name="productTypeId" ,
		foreignKey=@ForeignKey(name="fk_PlanRevenue_productType_id"))
	private ProductType productType;
	
	@Column
	private Double amount;
	
	
	public PlanRevenue(){};
	
	

	public PlanRevenue(Integer storeId, Integer year, Integer month,  Double amount) {
		this.storeId = storeId;
		this.year = year;
		this.month = month;
		this.amount = amount;
	}



	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final Integer getStoreId() {
		return storeId;
	}

	public final void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public final Integer getYear() {
		return year;
	}

	public final void setYear(Integer year) {
		this.year = year;
	}

	public final Integer getMonth() {
		return month;
	}

	public final void setMonth(Integer month) {
		this.month = month;
	}

	public final ProductType getProductType() {
		return productType;
	}

	public final void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public final Double getAmount() {
		return amount;
	}

	public final void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}
