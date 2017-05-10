package com.yinzhiwu.springmvc3.dao.impl;

import java.util.List;

import org.hibernate.exception.DataNotFoundException;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.CustomerYzwDao;
import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;

@Repository
public class CustomerYzwDaoImpl extends BaseDaoImpl<CustomerYzw, Integer> implements CustomerYzwDao{

	@Override
	public CustomerYzw findByWeChat(String weChatNo) throws DataNotFoundException {
		List<CustomerYzw> list = findByProperty("weChat", weChatNo);
		if(list.size()> 0)
			return list.get(0);
		else
			throw new DataNotFoundException(CustomerYzwDaoImpl.class, "weChat", weChatNo);
	}

	@Override
	public CustomerYzw findByPhoneNo(String phoneNo) throws DataNotFoundException {
		List<CustomerYzw> list = findByProperty("mobilePhone", phoneNo);
		if(list.size() > 0)
			return list.get(0);
		else
			throw new DataNotFoundException(CustomerYzwDaoImpl.class, "mobilePhone", phoneNo);
	}

	
}
