package com.yinzhiwu.springmvc3.dao;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.income.IncomeRecord;
import com.yinzhiwu.springmvc3.entity.type.IncomeType;
import com.yinzhiwu.springmvc3.model.view.IncomeRecordApiView;

public interface IncomeRecordDao extends IBaseDao<IncomeRecord, Integer> {

	int findCountByIncomeTypesByBeneficiary(int distributerId, int[] incomeTypeIds);

	public IncomeRecord findExpProducedByEvent(Integer id, IncomeType exp);

	IncomeRecordApiView findApiViewById(int id);

	List<IncomeRecordApiView> getListFaster(int observerId, int eventTypeId, int relationTypeId, int incomeTypeId);

	void testCriteriaQuery();

	
}
