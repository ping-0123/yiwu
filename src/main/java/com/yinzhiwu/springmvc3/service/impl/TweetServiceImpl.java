package com.yinzhiwu.springmvc3.service.impl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.TweetDao;
import com.yinzhiwu.springmvc3.entity.BaseTypeDao;
import com.yinzhiwu.springmvc3.entity.Tweet;
import com.yinzhiwu.springmvc3.entity.TweetType;
import com.yinzhiwu.springmvc3.model.TweetModel;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.TweetAbbrApiView;
import com.yinzhiwu.springmvc3.model.view.TweetApiView;
import com.yinzhiwu.springmvc3.service.TweetService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TweetServiceImpl  extends BaseServiceImpl<Tweet, Integer> implements TweetService  {
	
	private static Log log = LogFactory.getLog(TweetServiceImpl.class);
	
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
		System.out.println(m.getCoverIconUrl() + "  " + m.getCoverIconUrl().length());
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

	@Override
	public YiwuJson<TweetApiView> findById(int id) {
		Tweet tweet = tweetDao.get(id);
		log.info("after get");
		return new YiwuJson<>(new TweetApiView(tweet));
	}
}
