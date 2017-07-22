package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.Tweet;
import com.yinzhiwu.yiwu.model.TweetModel;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.TweetAbbrApiView;
import com.yinzhiwu.yiwu.model.view.TweetApiView;

public interface TweetService extends IBaseService<Tweet, Integer> {

	int save(TweetModel m);

	YiwuJson<List<TweetAbbrApiView>> findByTypeByFuzzyTitle(int tweetTypeId, String title);

	YiwuJson<TweetApiView> findById(int id);

}
