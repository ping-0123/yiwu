package com.yinzhiwu.springmvc3.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.CustomerDao;
import com.yinzhiwu.springmvc3.dao.impl.DistributerDaoImpl;
import com.yinzhiwu.springmvc3.entity.Customer;

@Repository
public class CustomerServiceImpl implements CustomerService {
	
	private static final Log logger = LogFactory.getLog(CustomerServiceImpl.class);
	
	@Autowired
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
