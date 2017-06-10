package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.income.DistributerIncome;
import com.yinzhiwu.springmvc3.entity.income.IncomeRecord;
import com.yinzhiwu.springmvc3.entity.type.IncomeType;

public interface DistributerIncomeService extends IBaseService<DistributerIncome, Integer>{

	void update_by_record(IncomeRecord incomeRecord);
	
}
