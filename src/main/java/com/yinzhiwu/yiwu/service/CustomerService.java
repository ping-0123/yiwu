package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.yzwOld.Customer;

public interface CustomerService {
	
	public Customer findByWeChat(String weChat);
}
