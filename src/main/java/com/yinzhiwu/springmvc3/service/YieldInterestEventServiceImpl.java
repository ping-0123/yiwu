package com.yinzhiwu.springmvc3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.springmvc3.dao.YieldInterestEventDao;
import com.yinzhiwu.springmvc3.entity.income.DistributerIncome;
import com.yinzhiwu.springmvc3.entity.income.YieldInterestEvent;
import com.yinzhiwu.springmvc3.entity.type.EventType;
import com.yinzhiwu.springmvc3.entity.type.IncomeType;
import com.yinzhiwu.springmvc3.service.impl.BaseServiceImpl;

@Service
public class YieldInterestEventServiceImpl extends BaseServiceImpl<YieldInterestEvent, Integer>
	implements YieldInterestEventService{
	
	@Autowired private IncomeEventService incomeEventService;

	@Autowired
	public void setBaseDao(YieldInterestEventDao yieldInterestEventDao){
		super.setBaseDao(yieldInterestEventDao);
	}
	
	@Override
	public void saveYieldInterest(DistributerIncome dIncome){
		Assert.notNull(dIncome);
		Assert.isTrue(IncomeType.BROKERAGE.equals(dIncome.getIncomeType()));
		Assert.notNull(dIncome.getDistributer());
		
		YieldInterestEvent event = new YieldInterestEvent(
				dIncome.getDistributer(), EventType.YIELD_INTEREST, dIncome.getIncome());
		incomeEventService.save(event);
	}
}
