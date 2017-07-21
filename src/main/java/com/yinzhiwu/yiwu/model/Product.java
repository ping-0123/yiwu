package com.yinzhiwu.yiwu.model;

import java.math.BigDecimal;
import java.util.Date;

public class Product {

	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private BigDecimal price;
	private Date productionDate;
	
	
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getDescription() {
		return description;
	}
	public final void setDescription(String description) {
		this.description = description;
	}
	public final BigDecimal getPrice() {
		return price;
	}
	public final void setPrice(BigDecimal price) {
		this.price = price;
	}
	public final Date getProductionDate() {
		return productionDate;
	}
	public final void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}
	public static final long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
