package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.Customer;

public interface CustomerService {
	
	public Customer findByWeChat(String weChat);
}
