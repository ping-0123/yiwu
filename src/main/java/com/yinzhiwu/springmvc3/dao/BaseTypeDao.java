package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.type.BaseType;
import com.yinzhiwu.springmvc3.entity.type.EventType;

public interface BaseTypeDao extends IBaseDao<BaseType, Integer> {

	EventType find_event_type_register_without_invatation_code();

	EventType find_event_type_register_with_invatation_code();
	
}
