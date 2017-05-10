package com.yinzhiwu.springmvc3.dao;

import org.hibernate.exception.DataNotFoundException;

import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;

public interface CustomerYzwDao extends IBaseDao<CustomerYzw, Integer>{
	public CustomerYzw findByWeChat(String weChatNo) throws DataNotFoundException;
	public CustomerYzw findByPhoneNo(String phoneNo) throws DataNotFoundException;
	
}
