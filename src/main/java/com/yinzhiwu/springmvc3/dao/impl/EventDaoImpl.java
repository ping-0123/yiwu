package com.yinzhiwu.springmvc3.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.EventDao;
import com.yinzhiwu.springmvc3.entity.Event;

@Repository
public class EventDaoImpl extends BaseDaoImpl<Event,Integer> implements EventDao{

}
