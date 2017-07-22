package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.income.DistributerIncome;
import com.yinzhiwu.yiwu.entity.income.IncomeRecord;

public interface DistributerIncomeService extends IBaseService<DistributerIncome, Integer>{

	void update_by_record(IncomeRecord incomeRecord);
	
}
