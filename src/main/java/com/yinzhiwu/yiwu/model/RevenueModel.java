package com.yinzhiwu.yiwu.model;

import java.sql.Date;

public class RevenueModel {

	private Integer storeId;
	
	private String storeName;

	private Date date;
	
	private Double amount;
	
	

	public RevenueModel(Integer storeId, String storeName, Date date, Double amount) {
		this.storeId = storeId;
		this.storeName = storeName;
		this.date = date;
		this.amount = amount;
	}

	public RevenueModel() {
	}

	public final Integer getStoreId() {
		return storeId;
	}

	public final void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public final String getStoreName() {
		return storeName;
	}

	public final void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public final Date getDate() {
		return date;
	}

	public final void setDate(Date date) {
		this.date = date;
	}

	public final Double getAmount() {
		return amount;
	}

	public final void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}
