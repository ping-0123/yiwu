package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.Tweet;

public interface TweetDao extends IBaseDao<Tweet, Integer> {

	List<Tweet> find_by_tweet_type_by_fuzzy_title(int tweetTypeId, String title);

}
