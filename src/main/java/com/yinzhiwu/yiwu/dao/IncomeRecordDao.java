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

	/**
	 * get the number of income records of observer whose id is observerId
	 * @ApiOperation(value = "获取经验,基金,佣金等收益记录数量	\n"
			+ "获取一级客户数量: ?observerId={distributerId}&eventTypeIds=10004&relationTypeIds=10016&incomeTypeIds=10012  \n"
			+ "获取二级客户数量:	?observerId={distributerId}&eventTypeIds=10004&relationTypeIds=10017&incomeTypeIds=10012	\n"
			+ "获取一级客户成交单数: ?observerId={distributerId}&eventTypeIds=10007&relationTypeIds=10016&incomeTypeIds=10014	\n"
			+ "获取二级客户成交单数: ?observerId={distributerId}&eventTypeIds=10007&relationTypeIds=10017&incomeTypeIds=10014	\n"
			+ "获取一级+二级客户成交单数: ?observerId={distributerId}&eventTypeIds=10007&relationTypeIds=10016,10017&incomeTypeIds=10014	\n"
			+ "获取一级客户转发微信次数: ?observerId={distributerId}&eventTypeIds=10005&relationTypeIds=10016&incomeTypeIds=10012	\n"
			+ "获取本人转发微信的次数: ?observerId={distributerId}&eventTypeIds=10005&relationTypeIds=10015&incomeTypeIds=10012	\n"
			)
	 * @param observerId
	 * @param eventTypeIds
	 * @param relationTypeIds
	 * @param incomeTypeIds
	 * @return
	 */
	Long findCountBy_incomeTypes_relationTypes_eventTypes_benificiary(int observerId, List<Integer> eventTypeIds,
			List<Integer> relationTypeIds, List<Integer> incomeTypeIds);

}
