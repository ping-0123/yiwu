package com.yinzhiwu.springmvc3.dao;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.Customer;

public interface CustomerDao extends IBaseDao<Customer, Integer> {

	 public List<Customer> findByProperty(String propertyName, Object value);
	 public Customer findByWeChat(String weChatNo);
	 
}
