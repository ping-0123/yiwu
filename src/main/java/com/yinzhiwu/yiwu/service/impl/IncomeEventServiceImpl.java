package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.IncomeEventDao;
import com.yinzhiwu.yiwu.entity.income.IncomeEvent;
import com.yinzhiwu.yiwu.service.IncomeEventService;
import com.yinzhiwu.yiwu.service.IncomeRecordService;

@Service
public class IncomeEventServiceImpl extends BaseServiceImpl<IncomeEvent, Integer> implements IncomeEventService {

	@Autowired
	private IncomeRecordService incomeRecordService;

	@Autowired
	public void setBaseDao(IncomeEventDao incomeEventDao) {
		super.setBaseDao(incomeEventDao);
	}

	@Override
	public Integer save(IncomeEvent event) {
		super.save(event);
		incomeRecordService.save_records_produced_by_event(event);
		return event.getId();
	}

}
