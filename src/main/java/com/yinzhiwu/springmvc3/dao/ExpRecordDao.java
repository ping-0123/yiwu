package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.ExpRecord;

public interface ExpRecordDao extends IBaseDao<ExpRecord, Integer>{





	int findMyShareTweetTimes(int beneficiary_id);

	int findSubordinateShareTweetTimes(int beneficiary_id);

	int findSecondaryRegisterTimes(int beneficiary_id);

	int findSubordinateRegisterTimes(int beneficiary_id);
	
}
