package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;

public interface CustomerYzwDao extends IBaseDao<CustomerYzw, Integer> {
	public CustomerYzw findByWeChat(String weChatNo);

	public CustomerYzw findByPhoneNo(String phoneNo);

}
