package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.income.DistributerIncome;
import com.yinzhiwu.yiwu.enums.IncomeType;

public interface DistributerIncomeDao extends IBaseDao<DistributerIncome, Integer> {

	DistributerIncome find_by_distributer_by_income_type(Integer distributerId, Integer incomeTypeId);

	Float get_beat_rate(IncomeType incomeType, float expValue);

	Float findCurrentValue(Integer distributerId, Integer incomeTypeId);

	/**
	 * 
	 * @param incomeTypeId
	 *            incomeTypeId in(10012, 10013,10014) 10012 represents exp
	 *            income type, 10013 represents funds income type, 10014
	 *            represents brokerage income type
	 * @param topN
	 *            topN>0
	 * @return
	 */
	List<DistributerIncome> getTopN(int incomeTypeId, int topN);

	List<DistributerIncome> findByIncomeType(IncomeType brokerage);

}
