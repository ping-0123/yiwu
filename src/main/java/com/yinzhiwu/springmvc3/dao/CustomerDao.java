package com.yinzhiwu.springmvc3.dao;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.Customer;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;

public interface CustomerDao extends IBaseDao<Customer, Integer> {

	 public List<Customer> findByProperty(String propertyName, Object value);
	public Customer findByWeChat(String weChatNo) throws DataNotFoundException;
	public Customer findByPhoneNo(String phoneNo) throws DataNotFoundException;
	 
}
