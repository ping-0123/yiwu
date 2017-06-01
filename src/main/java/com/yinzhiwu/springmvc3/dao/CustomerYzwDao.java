package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;

public interface CustomerYzwDao extends IBaseDao<CustomerYzw, Integer>{
	public CustomerYzw findByWeChat(String weChatNo) throws DataNotFoundException;
	public CustomerYzw findByPhoneNo(String phoneNo) throws DataNotFoundException;
	
}
