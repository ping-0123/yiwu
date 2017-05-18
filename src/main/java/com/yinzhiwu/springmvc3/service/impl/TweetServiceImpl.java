package com.yinzhiwu.springmvc3.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.TweetDao;
import com.yinzhiwu.springmvc3.entity.BaseTypeDao;
import com.yinzhiwu.springmvc3.entity.Tweet;
import com.yinzhiwu.springmvc3.entity.TweetType;
import com.yinzhiwu.springmvc3.model.TweetModel;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.TweetAbbrApiView;
import com.yinzhiwu.springmvc3.service.TweetService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TweetServiceImpl  extends BaseServiceImpl<Tweet, Integer> implements TweetService  {
	
	@Autowired
	private TweetDao tweetDao;
	
	@Autowired
	private BaseTypeDao baseTypeDao;

	@Autowired
	public void setBaseDao(TweetDao tweetDao){
		super.setBaseDao(tweetDao);
	}
	
	@Override
	public int save(TweetModel m){
		Tweet tweet = new Tweet(m);
		TweetType type = (TweetType) baseTypeDao.get(m.getTweetTypeId());
		tweet.setTweetType(type);
		
		return tweetDao.save(tweet);
	}

	@Override
	public YiwuJson<List<TweetAbbrApiView>> findByTypeByFuzzyTitle(int tweetTypeId, String title) {
		List<Tweet> tweets = tweetDao.find_by_tweet_type_by_fuzzy_title(tweetTypeId, title);
		List<TweetAbbrApiView> views = new ArrayList<>();
		for (Tweet t : tweets) {
			views.add(new TweetAbbrApiView(t));
		}
		return new YiwuJson<>(views);
	}
}
