package com.yinzhiwu.springmvc3.service;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.Tweet;
import com.yinzhiwu.springmvc3.model.TweetModel;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.TweetAbbrApiView;

public interface TweetService extends IBaseService<Tweet, Integer> {

	int save(TweetModel m);

	YiwuJson<List<TweetAbbrApiView>> findByTypeByFuzzyTitle(int tweetTypeId, String title);

}
