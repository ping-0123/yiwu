package com.yinzhiwu.springmvc3.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.EventTypeDao;
import com.yinzhiwu.springmvc3.entity.type.EventType;

@Repository
public class EventTypeDaoImpl extends BaseDaoImpl<EventType, Integer> implements EventTypeDao{

}
