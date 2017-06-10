package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.income.IncomeEvent;
import com.yinzhiwu.springmvc3.entity.income.IncomeRecord;

public interface IncomeRecordService  extends IBaseService<IncomeRecord,Integer>{

	void save_records_produced_by_event(IncomeEvent event);

}
