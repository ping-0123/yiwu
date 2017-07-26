package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.ShareTweetEventDao;
import com.yinzhiwu.yiwu.entity.income.ShareTweetEvent;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.service.IncomeEventService;
import com.yinzhiwu.yiwu.service.ShareTweetEventService;

@Service
public class ShareTweetEventServiceImpl extends BaseServiceImpl<ShareTweetEvent, Integer>
		implements ShareTweetEventService {

	@Autowired
	private IncomeEventService incomeEventService;

	@Autowired
	private ShareTweetEventDao shareTweetEventDao;

	@Autowired
	private void setBaseDao(ShareTweetEventDao shareTweetEventDao) {
		super.setBaseDao(shareTweetEventDao);
	}

	@Override
	public Integer save(ShareTweetEvent e) {
		Assert.notNull(e);
		Assert.notNull(e.getDistributer());
		Assert.notNull(e.getTweet());

		/*
		 * set event type
		 */
		long times = shareTweetEventDao.findDailyShareTimes(e.getDistributer().getId(), e.getOccurTime());
		e.setOrdinalOfDay((short) (times + 1));
		if (times < 3)
			e.setType(EventType.SHARE_TWEET_BY_WECHAT_FIRST_THREE_TIMES_PER_DAY);
		else
			e.setType(EventType.SHARE_TWEET_BY_WECHAT_AFTER_THREE_TIMES_PER_DAY);

		return incomeEventService.save(e);
	}

}
