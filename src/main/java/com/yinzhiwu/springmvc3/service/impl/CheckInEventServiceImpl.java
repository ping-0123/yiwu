package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.yinzhiwu.springmvc3.dao.CheckInEventDao;
import com.yinzhiwu.springmvc3.entity.income.CheckInEvent;
import com.yinzhiwu.springmvc3.service.CheckInEventService;
import com.yinzhiwu.springmvc3.service.IncomeEventService;

@Repository
public class CheckInEventServiceImpl extends BaseServiceImpl<CheckInEvent, Integer> implements CheckInEventService {

	@Autowired public void setBaseDao(CheckInEventDao checkInEventDao){
		super.setBaseDao(checkInEventDao);
	}
	
	@Autowired private IncomeEventService incomeEventService;
	
	@Override
	public Integer save(CheckInEvent event){
		Assert.notNull(event);
		Assert.notNull(event.getType());
		Assert.notNull(event.getDistributer());
		
		return incomeEventService.save(event);
	}
}
