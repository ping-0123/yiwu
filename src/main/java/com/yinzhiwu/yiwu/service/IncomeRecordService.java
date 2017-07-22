package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.income.IncomeEvent;
import com.yinzhiwu.yiwu.entity.income.IncomeRecord;
import com.yinzhiwu.yiwu.model.view.IncomeRecordApiView;
import com.yinzhiwu.yiwu.model.view.ShareTweetIncomeRecordApiView;

public interface IncomeRecordService  extends IBaseService<IncomeRecord,Integer>{

	void save_records_produced_by_event(IncomeEvent event);

	int findCountByIncomeTypesByBeneficiary(int distributerId, int[] incomeTypeIds);

	List<IncomeRecordApiView> getListFaster(int observerId, int eventTypeId, int relationTypeId, int incomeTypeId);

	List<ShareTweetIncomeRecordApiView> getShareTweetRecordApiViews(int beneficiaryId, int[] eventTypeIds,
			int[] relationTypeIds, int[] incomeTypeIds);

}
