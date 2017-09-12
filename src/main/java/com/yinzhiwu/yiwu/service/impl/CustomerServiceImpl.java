package com.yinzhiwu.yiwu.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.CustomerDao;
import com.yinzhiwu.yiwu.entity.yzwOld.Customer;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.service.CustomerService;

@Service
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
