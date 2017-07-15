package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.income.DistributerIncome;
import com.yinzhiwu.springmvc3.entity.income.YieldInterestEvent;

public interface YieldInterestEventService extends IBaseService<YieldInterestEvent, Integer> {

	void saveYieldInterest(DistributerIncome dIncome);

	void saveYieldInterestByEveryDay();

}
