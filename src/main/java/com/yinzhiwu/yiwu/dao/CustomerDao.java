package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzwOld.Customer;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

public interface CustomerDao extends IBaseDao<Customer, Integer> {

	 public List<Customer> findByProperty(String propertyName, Object value);
	public Customer findByWeChat(String weChatNo) throws DataNotFoundException;
	public Customer findByPhoneNo(String phoneNo) throws DataNotFoundException;
	 
}
