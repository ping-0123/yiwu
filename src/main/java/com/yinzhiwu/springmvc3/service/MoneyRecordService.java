package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.MoneyRecord;

public interface MoneyRecordService extends IBaseService<MoneyRecord, Integer>{
	
	public void saveRegisterFundsRecord(Distributer beneficiary, Distributer contributor);
}
