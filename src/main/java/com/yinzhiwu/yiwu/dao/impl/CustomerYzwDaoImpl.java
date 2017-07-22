package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.CustomerYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

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
