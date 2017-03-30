package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.OrderDao;
import com.yinzhiwu.springmvc3.entity.Customer;
import com.yinzhiwu.springmvc3.entity.Interest;
import com.yinzhiwu.springmvc3.service.InterestService;

@Service
public class InterestServiceImpl
	implements InterestService
{

	@Autowired
	private OrderDao orderDao;
	
	@Override
	public Interest getInterest(Customer c) {
		return null;
	}

}
