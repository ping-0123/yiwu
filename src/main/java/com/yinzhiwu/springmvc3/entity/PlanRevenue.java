package com.yinzhiwu.springmvc3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="plan_revenue", 
		uniqueConstraints={@UniqueConstraint(columnNames={"storeId","year", "month", "productTypeId"})})
public class PlanRevenue {

	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private Integer storeId;
	
	@Column
	private Integer year;
	
	@Column
	private Integer month;
	
	@ManyToOne
	@JoinColumn(name="productTypeId" ,
		foreignKey=@ForeignKey(name="fk_op_productTypeId"))
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
