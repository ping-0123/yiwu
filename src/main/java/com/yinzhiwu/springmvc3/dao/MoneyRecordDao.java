package com.yinzhiwu.springmvc3.dao;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.MoneyRecord;

public interface MoneyRecordDao extends IBaseDao<MoneyRecord, Integer>{


	public int findSubordinateOrderTimes(int beneficiary_id);

	public int findSecondaryOrderTimes(int beneficiary_id);

	/**
	 * 获取收益人的所有的资金进出入的记录总数
	 * @param beneficiatyId
	 * @return
	 */
	public int findCountByBeneficiatyId(int beneficiatyId);

	public List<MoneyRecord> findByTypesByBeneficiaryId(int benificiaryId, List<Integer> typeIds);

	public List<MoneyRecord> findByBeneficaryIdBySubordiatesOrderTypes(int benificiaryId);

	public List<MoneyRecord> findByBeneficaryIdBySecondariesOrderTypes(int benificiaryId);
}
	
