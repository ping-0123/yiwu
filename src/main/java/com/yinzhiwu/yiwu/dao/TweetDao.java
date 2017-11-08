package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.Tweet;
import com.yinzhiwu.yiwu.enums.TweetType;

public interface TweetDao extends IBaseDao<Tweet, Integer> {

	List<Tweet> findByFuzzyTitle(String title);

	List<Tweet> findByTypeAndFunzzyTitle(TweetType type, String title);

}
