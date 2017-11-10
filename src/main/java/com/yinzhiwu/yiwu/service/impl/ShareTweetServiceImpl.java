package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.ShareTweetDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.ShareTweet;
import com.yinzhiwu.yiwu.entity.Tweet;
import com.yinzhiwu.yiwu.service.ShareTweetService;

/**
*@Author ping
*@Time  创建时间:2017年11月9日下午4:58:40
*
*/

@Service
public class ShareTweetServiceImpl extends BaseServiceImpl<ShareTweet, Integer> implements ShareTweetService{
	
	@Autowired ShareTweetDao shareTweetDao;
	@Autowired private ApplicationContext applicationContext;
	
	@Autowired public void setBaseDao(ShareTweetDao dao){super.setBaseDao(dao);}

	/**
	 * listened by {@link IncomeRecordServiceImpl#handleShareTweet(ShareTweet)}
	 */
	@Override
	public void doShareTweet(Distributer distributer, Tweet tweet) {
		ShareTweet shareTweet = new ShareTweet();
		shareTweet.setDistributer(distributer);
		shareTweet.setTweet(tweet);
		shareTweet.setOrdinalNo(1 + shareTweetDao.getSumSharedTimesOfCurrentDay(distributer.getId()));
		
		save(shareTweet);
		
		applicationContext.publishEvent(shareTweet);
	}

	@Override
	public Long findSumShareTimes(Distributer distributer) {
		return shareTweetDao.findCountByDistributerId(distributer.getId());
	}
	
}
