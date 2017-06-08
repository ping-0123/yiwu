package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.Event;
import com.yinzhiwu.springmvc3.entity.IncomeRecord;

public interface IncomeRecordService  extends IBaseService<IncomeRecord,Integer>{

	void save_records_produced_by_event(Event event);

}
