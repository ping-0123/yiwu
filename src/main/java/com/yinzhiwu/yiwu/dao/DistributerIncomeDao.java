package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.income.DistributerIncome;
import com.yinzhiwu.yiwu.enums.IncomeType;

public interface DistributerIncomeDao extends IBaseDao<DistributerIncome, Integer> {

	Float calculateBeatRatio(IncomeType type, Float value);

	List<DistributerIncome> getTopN(IncomeType type, int topN);

}
