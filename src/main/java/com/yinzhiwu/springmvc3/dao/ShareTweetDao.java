package com.yinzhiwu.springmvc3.dao;

import java.util.Date;
import java.util.List;

import com.yinzhiwu.springmvc3.entity.ShareTweet;

public interface ShareTweetDao extends IBaseDao<ShareTweet, Integer> {

	int findCountBySharerByDate(int distributerId, Date date);

	List<ShareTweet> findBySharerId(int sharerId);

	List<ShareTweet> findBySharerIds(List<Integer> subordiateIds);


}
