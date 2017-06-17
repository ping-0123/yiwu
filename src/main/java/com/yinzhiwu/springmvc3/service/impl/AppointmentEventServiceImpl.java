package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.springmvc3.dao.AppointmentDao;
import com.yinzhiwu.springmvc3.dao.AppointmentEventDao;
import com.yinzhiwu.springmvc3.entity.income.AbstractAppointmentEvent;
import com.yinzhiwu.springmvc3.service.AppointmentEventService;
import com.yinzhiwu.springmvc3.service.IncomeEventService;

@Service
public class AppointmentEventServiceImpl extends BaseServiceImpl<AbstractAppointmentEvent,Integer> implements AppointmentEventService {

	@Autowired public void setBaseDao(AppointmentEventDao appointmentEventDao){
		super.setBaseDao(appointmentEventDao);
	}
	@Autowired private AppointmentDao appointmentDao;
	@Autowired private IncomeEventService incomeEventService;
	
	/**
	 * 调用该函数前，先判断是否满足预约， 取消预约条件
	 */
	@Override
	public Integer save(AbstractAppointmentEvent event){
		Assert.notNull(event);
		Assert.notNull(event.getDistributer());
		Assert.notNull(event.getType());
		Assert.notNull(event.getLesson());
		
		incomeEventService.save(event);
		
		
		return null;
	}
	
	
}
