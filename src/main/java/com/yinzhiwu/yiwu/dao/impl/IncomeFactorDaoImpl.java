package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.IncomeFactorDao;
import com.yinzhiwu.yiwu.entity.income.IncomeFactor;
import com.yinzhiwu.yiwu.event.IncomeEventType;

@Repository
public class IncomeFactorDaoImpl extends BaseDaoImpl<IncomeFactor, Integer> implements IncomeFactorDao {


	@Override
	public List<IncomeFactor> findByEventType(IncomeEventType type) {
		return findByProperty("incomeType",type);
	}

}
