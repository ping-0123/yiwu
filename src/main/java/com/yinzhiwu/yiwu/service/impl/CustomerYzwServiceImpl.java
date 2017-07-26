package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.CustomerYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.service.CustomerYzwService;

@Service
public class CustomerYzwServiceImpl extends BaseServiceImpl<CustomerYzw, Integer> implements CustomerYzwService {

	@Autowired
	public void setBaseDao(CustomerYzwDao customerYzwDao) {
		super.setBaseDao(customerYzwDao);
	}
}
