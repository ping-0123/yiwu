package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.Tweet;
import com.yinzhiwu.yiwu.enums.TweetType;

public interface TweetService extends IBaseService<Tweet, Integer> {

	List<Tweet> findByFuzzyTitle(String title);

	List<Tweet> findByTypeAndFunzzyTitle(TweetType type, String title);

}
