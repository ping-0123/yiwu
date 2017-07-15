package com.yinzhiwu.springmvc3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.springmvc3.dao.DistributerIncomeDao;
import com.yinzhiwu.springmvc3.dao.YieldInterestEventDao;
import com.yinzhiwu.springmvc3.entity.income.DistributerIncome;
import com.yinzhiwu.springmvc3.entity.income.YieldInterestEvent;
import com.yinzhiwu.springmvc3.entity.type.IncomeType;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.service.impl.BaseServiceImpl;

@Service
public class YieldInterestEventServiceImpl extends BaseServiceImpl<YieldInterestEvent, Integer>
	implements YieldInterestEventService{
	
	@Autowired private IncomeEventService incomeEventService;
	@Autowired private DistributerIncomeDao distributerIncomeDao;

	@Autowired
	public void setBaseDao(YieldInterestEventDao yieldInterestEventDao){
		super.setBaseDao(yieldInterestEventDao);
	}
	
	@Override
	public void saveYieldInterest(DistributerIncome dIncome){
		Assert.notNull(dIncome);
		Assert.isTrue(IncomeType.BROKERAGE.equals(dIncome.getIncomeType()));
		Assert.notNull(dIncome.getDistributer());
		
		YieldInterestEvent event = new YieldInterestEvent(dIncome.getDistributer(), dIncome.getIncome());
		incomeEventService.save(event);
	}
	
	@Override
	public void saveYieldInterestByEveryDay(){
		try {
			List<DistributerIncome> distributerIncomes = distributerIncomeDao.findByProperty("incomeType.id", IncomeType.BROKERAGE.getId());
			for (DistributerIncome distributerIncome : distributerIncomes) {
				saveYieldInterest(distributerIncome);
			}
		} catch (DataNotFoundException e) {
			return;
		}
	}
}
