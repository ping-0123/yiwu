package com.test.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.dao.ShareTweetDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.Tweet;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.service.DistributerService;
import com.yinzhiwu.yiwu.service.ShareTweetService;
import com.yinzhiwu.yiwu.service.TweetService;

/**
*@Author ping
*@Time  创建时间:2017年11月20日上午9:57:40
*
*/

public class ShareTweetDaoTest extends BaseSpringTest {
	
	@Autowired private ShareTweetDao shareTweetDao;
	@Autowired private ShareTweetService shareTweetService;
	@Autowired private DistributerService distributerService;
	@Autowired private TweetService tweetService;
	
	@Test
	public void testGetSumSharedTimesOfCurrentDay(){
		int distributerId = 3000217;
		int times = shareTweetDao.getSumSharedTimesOfCurrentDay(distributerId);
		System.err.println("times is " + times);
	}
	
	@Test
	@Rollback(false)
	public void testShareTweet() throws DataNotFoundException{
		Distributer distributer = distributerService.get(3000217);
		Tweet tweet = tweetService.get(12000040);
		
		shareTweetService.doShareTweet(distributer, tweet);
		shareTweetService.doShareTweet(distributer, tweet);
		shareTweetService.doShareTweet(distributer, tweet);
	}
}
