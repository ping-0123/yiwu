package com.yinzhiwu.springmvc3.dao;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.income.IncomeFactor;
import com.yinzhiwu.springmvc3.entity.type.EventType;

public interface IncomeFactorDao extends IBaseDao<IncomeFactor, Integer>{

	/**
	 * 查询能产生incomeTypeId收益类型的事件类型
	 * @param incomeTypeId
	 * @return
	 */
	List<EventType> findEventTypes(int incomeTypeId);

}
