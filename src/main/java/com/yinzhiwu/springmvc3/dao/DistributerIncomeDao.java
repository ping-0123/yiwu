package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.DistributerIncome;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;

public interface DistributerIncomeDao extends IBaseDao<DistributerIncome, Integer> {

	DistributerIncome find_by_distributer_by_income_type(Integer distributerId, Integer incomeTypeId) throws DataNotFoundException;
	
}
