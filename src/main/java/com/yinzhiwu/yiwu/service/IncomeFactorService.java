package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.income.IncomeFactor;
import com.yinzhiwu.yiwu.entity.type.EventType;

public interface IncomeFactorService extends IBaseService<IncomeFactor, Integer> {

	public List<EventType> getEventTypes(int incomeTypeId);

}
