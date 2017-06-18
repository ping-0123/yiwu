package com.yinzhiwu.springmvc3.model.view;

import java.sql.Date;

public class OrderOldApiView {
	
	private Date recordDate;
	
	private String productName;
	
	private float marckedPrice;
	
	private int count;
	
	private float payedAmount;
	
	private String payedMethod;
	
	private String customerName;
	
	private String auditOrChild;
	
	private String  vipAttr;
	
	private String salesmanName;

	public final Date getRecordDate() {
		return recordDate;
	}

	public final void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public final String getProductName() {
		return productName;
	}

	public final void setProductName(String productName) {
		this.productName = productName;
	}

	public final float getMarckedPrice() {
		return marckedPrice;
	}

	public final void setMarckedPrice(float marckedPrice) {
		this.marckedPrice = marckedPrice;
	}

	public final int getCount() {
		return count;
	}

	public final void setCount(int count) {
		this.count = count;
	}

	public final float getPayedAmount() {
		return payedAmount;
	}

	public final void setPayedAmount(float payedAmount) {
		this.payedAmount = payedAmount;
	}

	public final String getPayedMethod() {
		return payedMethod;
	}

	public final void setPayedMethod(String payedMethod) {
		this.payedMethod = payedMethod;
	}

	public final String getCustomerName() {
		return customerName;
	}

	public final void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public final String getAuditOrChild() {
		return auditOrChild;
	}

	public final void setAuditOrChild(String auditOrChild) {
		this.auditOrChild = auditOrChild;
	}

	public final String getVipAttr() {
		return vipAttr;
	}

	public final void setVipAttr(String vipAttr) {
		this.vipAttr = vipAttr;
	}

	public final String getSalesmanName() {
		return salesmanName;
	}

	public final void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}
	
	
}
