package com.yinzhiwu.yiwu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.IncomeFactorDao;
import com.yinzhiwu.yiwu.entity.income.IncomeFactor;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.service.IncomeFactorService;

@Service
public class IncomeFactorServiceImpl extends BaseServiceImpl<IncomeFactor, Integer> implements IncomeFactorService {

	@Autowired
	public void setBaseDao(IncomeFactorDao incomeFactorDao) {
		super.setBaseDao(incomeFactorDao);
	}

	@Autowired
	private IncomeFactorDao incomeFactorDao;

	@Override
	public List<EventType> getEventTypes(int incomeTypeId) {
		return incomeFactorDao.findEventTypes(incomeTypeId);
	}
}
