package com.yinzhiwu.yiwu.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.BaseTypeDao;
import com.yinzhiwu.yiwu.entity.type.BaseType;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

@Repository
public class BaseTypeDaoImpl extends BaseDaoImpl<BaseType, Integer> implements BaseTypeDao {

	@Override
	public EventType find_event_type_register_without_invatation_code() {
		try {
			return (EventType) findByProperty("name", EventType.REGISTER_WITHOUT_INVATATION_CODE.getName());
		} catch (DataNotFoundException e) {
			logger.error("请初始化eventType 数据", e);
			return null;
		}
	}

	@Override
	public EventType find_event_type_register_with_invatation_code() {
		try {
			return (EventType) findByProperty("name", EventType.REGISTER_WITH_INVATATION_CODE.getName());
		} catch (DataNotFoundException e) {
			logger.error("请初始化eventType 数据", e);
			return null;
		}
	}

}
