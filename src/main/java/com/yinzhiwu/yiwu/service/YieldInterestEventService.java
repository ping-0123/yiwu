package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.income.DistributerIncome;
import com.yinzhiwu.yiwu.entity.income.YieldInterestEvent;

public interface YieldInterestEventService extends IBaseService<YieldInterestEvent, Integer> {

	void saveYieldInterest(DistributerIncome dIncome);

	void saveYieldInterestByEveryDay();

}
