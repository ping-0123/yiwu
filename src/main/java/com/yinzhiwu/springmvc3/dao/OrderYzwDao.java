package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;

public interface OrderYzwDao extends IBaseDao<OrderYzw, String> {
	public String find_last_id();
}
