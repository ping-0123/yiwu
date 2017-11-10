package com.yinzhiwu.yiwu.service;

import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.yiwu.entity.income.DistributerIncome;
import com.yinzhiwu.yiwu.entity.income.IncomeRecord;
import com.yinzhiwu.yiwu.enums.IncomeType;

public interface DistributerIncomeService extends IBaseService<DistributerIncome, Integer> {

	/**
	 * 收益记录后，更新收益值
	 * @param record
	 */
	@Transactional
	void updateIncome(IncomeRecord record);

	@Transactional
	float calculateBeatRatio(IncomeType brokerage, float brokerage2);

}
