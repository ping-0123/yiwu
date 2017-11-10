package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.ShareTweet;

/**
*@Author ping
*@Time  创建时间:2017年11月9日下午4:56:44
*
*/

public interface ShareTweetDao extends IBaseDao<ShareTweet,Integer> {

	int getSumSharedTimesOfCurrentDay(Integer distributerId);

	Long findCountByDistributerId(Integer distributerId);

}
