package com.yinzhiwu.yiwu.service;

import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.ShareTweet;
import com.yinzhiwu.yiwu.entity.Tweet;

/**
*@Author ping
*@Time  创建时间:2017年11月9日下午4:58:10
*
*/

public interface ShareTweetService extends IBaseService<ShareTweet,Integer> {

	@Transactional
	void doShareTweet(Distributer distributer, Tweet tweet);

	Long findSumShareTimes(Distributer distributer);

}
