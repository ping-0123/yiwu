package com.yinzhiwu.springmvc3.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.AppointmentEventDao;
import com.yinzhiwu.springmvc3.entity.income.AbstractAppointmentEvent;

@Repository
public class AppointmentEventDaoImpl extends BaseDaoImpl<AbstractAppointmentEvent,Integer> implements AppointmentEventDao{

}
