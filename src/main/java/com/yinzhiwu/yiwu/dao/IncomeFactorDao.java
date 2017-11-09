package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.income.IncomeFactor;
import com.yinzhiwu.yiwu.event.IncomeEventType;

public interface IncomeFactorDao extends IBaseDao<IncomeFactor, Integer> {

	/**
	 * 根据收益事件类型查询收益系数列表
	 * @param type
	 * @return
	 */
	List<IncomeFactor> findByEventType(IncomeEventType type);

}
