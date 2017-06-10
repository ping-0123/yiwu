package com.yinzhiwu.springmvc3.dao;

import java.util.Date;

import com.yinzhiwu.springmvc3.entity.income.ShareTweetEvent;

public interface ShareTweetEventDao extends IBaseDao<ShareTweetEvent, Integer> {

	long findDailyShareTimes(Integer distributerId, Date occurTime);

}
