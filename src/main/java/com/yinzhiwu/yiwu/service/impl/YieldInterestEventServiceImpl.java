package com.yinzhiwu.yiwu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.DistributerIncomeDao;
import com.yinzhiwu.yiwu.dao.YieldInterestEventDao;
import com.yinzhiwu.yiwu.entity.income.DistributerIncome;
import com.yinzhiwu.yiwu.entity.income.YieldInterestEvent;
import com.yinzhiwu.yiwu.entity.type.IncomeType;
import com.yinzhiwu.yiwu.service.IncomeEventService;
import com.yinzhiwu.yiwu.service.YieldInterestEventService;

@Service
public class YieldInterestEventServiceImpl extends BaseServiceImpl<YieldInterestEvent, Integer>
		implements YieldInterestEventService {

	@Autowired
	private IncomeEventService incomeEventService;
	@Autowired
	private DistributerIncomeDao distributerIncomeDao;

	@Autowired
	public void setBaseDao(YieldInterestEventDao yieldInterestEventDao) {
		super.setBaseDao(yieldInterestEventDao);
	}

	@Override
	public void saveYieldInterest(DistributerIncome dIncome) {
		Assert.notNull(dIncome);
		Assert.isTrue(IncomeType.BROKERAGE.equals(dIncome.getIncomeType()));
		Assert.notNull(dIncome.getDistributer());

		YieldInterestEvent event = new YieldInterestEvent(dIncome.getDistributer(), dIncome.getIncome());
		incomeEventService.save(event);
	}

	@Override
	public void saveYieldInterestByEveryDay() {
		List<DistributerIncome> distributerIncomes = distributerIncomeDao.findByIncomeType(IncomeType.BROKERAGE);
		for (DistributerIncome distributerIncome : distributerIncomes) {
			saveYieldInterest(distributerIncome);
		}
	}
}
