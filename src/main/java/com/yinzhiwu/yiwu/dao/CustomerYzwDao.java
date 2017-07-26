package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

public interface CustomerYzwDao extends IBaseDao<CustomerYzw, Integer> {
	public CustomerYzw findByWeChat(String weChatNo) throws DataNotFoundException;

	public CustomerYzw findByPhoneNo(String phoneNo) throws DataNotFoundException;

}
