package com.yinzhiwu.springmvc3.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.CustomerDao;
import com.yinzhiwu.springmvc3.dao.impl.DistributerDaoImpl;
import com.yinzhiwu.springmvc3.entity.Customer;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.service.CustomerService;

@Repository
public class CustomerServiceImpl implements CustomerService {
	
	private static final Log logger = LogFactory.getLog(CustomerServiceImpl.class);
	
	@Autowired
	@Qualifier("customerDaoImpl")
	private CustomerDao customerDao;

	@Override
	public Customer findByWeChat(String weChat) {
		try {
			return customerDao.findByWeChat(weChat);
		} catch (DataNotFoundException e) {
			logger.info(e.getMessage());
			return null;
		}
	}

}
