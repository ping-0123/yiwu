package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.DistributerIncome;
import com.yinzhiwu.springmvc3.entity.IncomeRecord;

public interface DistributerIncomeService extends IBaseService<DistributerIncome, Integer>{

	void update_by_record(IncomeRecord incomeRecord);

}
