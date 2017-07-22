package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.type.BaseType;
import com.yinzhiwu.yiwu.entity.type.EventType;

public interface BaseTypeDao extends IBaseDao<BaseType, Integer> {

	EventType find_event_type_register_without_invatation_code();

	EventType find_event_type_register_with_invatation_code();
	
}
