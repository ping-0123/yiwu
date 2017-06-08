package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.EventDao;
import com.yinzhiwu.springmvc3.entity.Event;
import com.yinzhiwu.springmvc3.service.IncomeRecordService;

@Service
public class EventServiceImpl extends BaseServiceImpl<Event, Integer> {
	
	
	@Autowired
	private IncomeRecordService incomeRecordService;
	
	@Autowired
	public void setBaseDao(EventDao eventDao){
		super.setBaseDao(eventDao);
	}
	
	@Override
	public Integer save(Event event){
		super.save(event);
		incomeRecordService.save_records_produced_by_event(event);
		return event.getId();	
	}
	
}
