
package com.yinzhiwu.yiwu.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.yinzhiwu.yiwu.dao.DepartmentDao;
import com.yinzhiwu.yiwu.entity.PlanRevenue;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

public class PlanRevenueApiModel {
	private int id;
	
	private int storeId;
	
	private String storeName;
	
	private int year;
	
	private int month;
	
	private int productTypeId;
	
	private String productTypeName;
	
	private Double amount;

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final int getStoreId() {
		return storeId;
	}

	public final void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public final String getStoreName() {
		return storeName;
	}

	public final void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public final int getYear() {
		return year;
	}

	public final void setYear(int year) {
		this.year = year;
	}

	public final int getMonth() {
		return month;
	}

	public final void setMonth(int month) {
		this.month = month;
	}

	public final int getProductTypeId() {
		return productTypeId;
	}

	public final void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
	}

	public final String getProductTypeName() {
		return productTypeName;
	}

	public final void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public final double getAmount() {
		return amount;
	}

	public final void setAmount(Double amount) {
		this.amount = amount;
	}

	public PlanRevenueApiModel() {
	}

	public PlanRevenueApiModel(@Autowired DepartmentDao dao, PlanRevenue plan){
		this.id = plan.getId();
		this.storeId = plan.getStoreId();
		try {
			this.storeName = dao.get(this.storeId).getDeptName();
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.year=plan.getYear();
		this.month = plan.getMonth();
		this.productTypeId = plan.getProductType().getId();
		this.productTypeName = plan.getProductType().getName();
		this.amount = plan.getAmount();
	}
	
	
}

