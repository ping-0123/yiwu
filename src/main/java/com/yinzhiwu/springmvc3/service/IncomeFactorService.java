package com.yinzhiwu.springmvc3.service;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.income.IncomeFactor;
import com.yinzhiwu.springmvc3.entity.type.EventType;

public interface IncomeFactorService extends IBaseService<IncomeFactor,Integer> {

	public List<EventType> getEventTypes(int incomeTypeId);

}
