package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.MoneyRecord;

public interface MoneyRecordDao extends IBaseDao<MoneyRecord, Integer>{


	public int findSubordinateOrderTimes(int beneficiary_id);

	public int findSecondaryOrderTimes(int beneficiary_id);
}
	
