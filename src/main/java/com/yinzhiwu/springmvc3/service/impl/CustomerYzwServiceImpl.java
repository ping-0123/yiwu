package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.CustomerYzwDao;
import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.service.CustomerYzwService;

@Service
public class CustomerYzwServiceImpl extends BaseServiceImpl<CustomerYzw, Integer> implements CustomerYzwService{

	@Autowired
	public void setBaseDao(CustomerYzwDao customerYzwDao){
		super.setBaseDao(customerYzwDao);
	}
}
