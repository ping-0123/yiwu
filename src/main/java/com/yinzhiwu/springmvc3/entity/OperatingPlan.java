package com.yinzhiwu.springmvc3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="operating_plan", 
		uniqueConstraints={@UniqueConstraint(columnNames={"storeId","year", "month", "productTypeId"})})
public class OperatingPlan {

	
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
	@JoinColumn(name="productTypeId")
	private ProductType productType;
	
	@Column
	private Double amount;
	
	
}
