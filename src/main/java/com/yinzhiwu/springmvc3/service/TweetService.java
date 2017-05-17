package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.Tweet;
import com.yinzhiwu.springmvc3.model.TweetModel;

public interface TweetService extends IBaseService<Tweet, Integer> {

	int save(TweetModel m);

}
