package com.yinzhiwu.springmvc3.dao;

import java.util.Date;

import com.yinzhiwu.springmvc3.entity.ShareTweet;

public interface ShareTweetDao extends IBaseDao<ShareTweet, Integer> {

	int findCountBySharerByDate(int distributerId, Date date);


}
