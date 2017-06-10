package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.IncomeEventDao;
import com.yinzhiwu.springmvc3.entity.income.IncomeEvent;
import com.yinzhiwu.springmvc3.service.IncomeEventService;
import com.yinzhiwu.springmvc3.service.IncomeRecordService;

@Service
public class IncomeEventServiceImpl extends BaseServiceImpl<IncomeEvent, Integer> implements IncomeEventService {
	
	
	@Autowired
	private IncomeRecordService incomeRecordService;
	
	@Autowired
	public void setBaseDao(IncomeEventDao incomeEventDao){
		super.setBaseDao(incomeEventDao);
	}
	
	@Override
	public Integer save(IncomeEvent event){
		super.save(event);
		incomeRecordService.save_records_produced_by_event(event);
		return event.getId();	
	}
	
}
