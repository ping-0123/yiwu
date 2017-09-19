package com.yinzhiwu.yiwu.dao;

import java.util.Date;

import com.yinzhiwu.yiwu.entity.income.ShareTweetEvent;

public interface ShareTweetEventDao extends IBaseDao<ShareTweetEvent, Integer> {

	long findDailyShareTimes(Integer distributerId, Date occurTime);

	int findShareTweetTimes(int distributerId);

	Long findCountByDistributerId(int distributerId);
}
