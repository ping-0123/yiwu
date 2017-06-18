package com.yinzhiwu.springmvc3.model.view;

import java.util.Date;

import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;

public class OrderAbbrApiView {

	private String id;
	
	private String productName;
	
	private Date buyDate;
	
	public OrderAbbrApiView(){};
	
	public OrderAbbrApiView(OrderYzw o){
		this.id = o.getId();
		this.productName=o.getProduct().getName();
		this.buyDate = o.getPayedDate();
	}

	public String getId() {
		return id;
	}

	public String getProductName() {
		return productName;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	
	
}
