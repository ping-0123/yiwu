package com.yinzhiwu.springmvc3.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.DistributerIncomeDao;
import com.yinzhiwu.springmvc3.entity.DistributerIncome;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;

@Repository
public class DistributerIncomeDaoImpl extends BaseDaoImpl<DistributerIncome, Integer> implements DistributerIncomeDao {

	@Override
	public DistributerIncome find_by_distributer_by_income_type(Integer distributerId, Integer incomeTypeId)
			throws DataNotFoundException {
		return findByProperties(
				new String[]{"distributer.id", "incomeType.id"}, 
				new Object[]{distributerId, incomeTypeId}).get(0);
	}

}
