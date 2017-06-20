package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.income.IncomeRecord;
import com.yinzhiwu.springmvc3.entity.type.IncomeType;

public interface IncomeRecordDao extends IBaseDao<IncomeRecord, Integer> {

	int findCountByIncomeTypesByBeneficiary(int distributerId, int[] incomeTypeIds);

	public IncomeRecord findExpProducedByEvent(Integer id, IncomeType exp);

	
}
