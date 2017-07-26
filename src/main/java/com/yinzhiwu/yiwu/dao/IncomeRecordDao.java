package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.income.IncomeRecord;
import com.yinzhiwu.yiwu.entity.type.IncomeType;
import com.yinzhiwu.yiwu.model.view.IncomeRecordApiView;
import com.yinzhiwu.yiwu.model.view.ShareTweetIncomeRecordApiView;

public interface IncomeRecordDao extends IBaseDao<IncomeRecord, Integer> {

	int findCountByIncomeTypesByBeneficiary(int distributerId, int[] incomeTypeIds);

	public IncomeRecord findExpProducedByEvent(Integer id, IncomeType exp);

	IncomeRecordApiView findApiViewById(int id);

	List<IncomeRecordApiView> getListFaster(int observerId, int eventTypeId, int relationTypeId, int incomeTypeId);

	void testCriteriaQuery();

	List<ShareTweetIncomeRecordApiView> getShareTweetRecordApiViews(int beneficiaryId, int[] eventTypeIds,
			int[] relationTypeIds, int[] incomeTypeIds);

}
