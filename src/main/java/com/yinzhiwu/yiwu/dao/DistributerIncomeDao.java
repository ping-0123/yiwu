package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.income.DistributerIncome;
import com.yinzhiwu.yiwu.enums.IncomeType;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;

public interface DistributerIncomeDao extends IBaseDao<DistributerIncome, Integer> {

	Float calculateBeatRatio(IncomeType type, Float value);

	List<DistributerIncome> getTopN(IncomeType type, int topN);

	List<DistributerIncome> findBrokerageIncomes();

	DistributerIncome findbyDistributerIdAndIncomeType(Integer distributerId, IncomeType type) throws DataNotFoundException;

}
