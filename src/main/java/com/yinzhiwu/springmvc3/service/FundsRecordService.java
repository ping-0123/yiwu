package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.FundsRecordType;

public interface FundsRecordService extends MoneyRecordService{


	void saveFundsRecord(Distributer beneficiary, Distributer contributor, float value, FundsRecordType type);

}
