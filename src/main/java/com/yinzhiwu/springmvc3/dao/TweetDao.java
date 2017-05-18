package com.yinzhiwu.springmvc3.dao;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.Tweet;

public interface TweetDao extends IBaseDao<Tweet, Integer>{

	List<Tweet> find_by_tweet_type_by_fuzzy_title(int tweetTypeId, String title);

}
