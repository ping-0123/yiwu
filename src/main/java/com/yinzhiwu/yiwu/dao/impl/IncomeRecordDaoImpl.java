package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.IncomeRecordDao;
import com.yinzhiwu.yiwu.entity.income.IncomeRecord;
import com.yinzhiwu.yiwu.enums.IncomeType;
import com.yinzhiwu.yiwu.model.view.IncomeRecordApiView;
import com.yinzhiwu.yiwu.model.view.ShareTweetIncomeRecordApiView;

@Repository
public class IncomeRecordDaoImpl extends BaseDaoImpl<IncomeRecord, Integer> implements IncomeRecordDao {

	@Override
	public int findCountByIncomeTypesByBeneficiary(int distributerId, int[] incomeTypeIds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IncomeRecord findExpProducedByEvent(Integer id, IncomeType exp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IncomeRecordApiView findApiViewById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IncomeRecordApiView> getListFaster(int observerId, int eventTypeId, int relationTypeId,
			int incomeTypeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void testCriteriaQuery() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ShareTweetIncomeRecordApiView> getShareTweetRecordApiViews(int beneficiaryId, int[] eventTypeIds,
			int[] relationTypeIds, int[] incomeTypeIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long findCountBy_incomeTypes_relationTypes_eventTypes_benificiary(int observerId, List<Integer> eventTypeIds,
			List<Integer> relationTypeIds, List<Integer> incomeTypeIds) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
