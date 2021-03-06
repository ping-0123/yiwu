package com.yinzhiwu.yiwu.web.purchase.dto;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;

/**
*@Author ping
*@Time  创建时间:2017年7月27日上午11:27:47
*
*/

public class CustomerDto {

	 private int customerId;
	 private String name;
	 private String phoneNo;
	 
	 
	 public static CustomerDto fromDistributer(Distributer d){
		 CustomerDto v = new CustomerDto();
		 v.setName(d.getName());
		 v.setPhoneNo(d.getPhoneNo());
		 CustomerYzw c = d.getCustomer();
		 if(c!=null)
			 v.setCustomerId(c.getId());
		 
		 return v;
	 }
	 
	 public CustomerDto(){}
	 public CustomerDto(Distributer distributer){
		 if(distributer == null) throw new IllegalArgumentException();
		 this.name = distributer.getName();
		 this.phoneNo = distributer.getPhoneNo();
		 CustomerYzw customer = distributer.getCustomer();
	 	if(customer!=null)
	 		this.customerId = customer.getId();
	 }
	 
	public int getCustomerId() {
		return customerId;
	}
	public String getName() {
		return name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public CustomerDto(int customerId, String name, String phoneNo) {
		this.customerId = customerId;
		this.name = name;
		this.phoneNo = phoneNo;
	}
	 
	 
}
