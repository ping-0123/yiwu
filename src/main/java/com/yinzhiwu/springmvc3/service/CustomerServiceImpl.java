package com.yinzhiwu.springmvc3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.CustomerDao;
import com.yinzhiwu.springmvc3.entity.Customer;

@Repository
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDao customerDao;

	@Override
	public Customer findByWeChat(String weChat) {
		return customerDao.findByWeChat(weChat);
	}

}
