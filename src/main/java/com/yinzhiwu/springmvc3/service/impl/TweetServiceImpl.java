package com.yinzhiwu.springmvc3.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.BaseTypeDao;
import com.yinzhiwu.springmvc3.dao.TweetDao;
import com.yinzhiwu.springmvc3.entity.Tweet;
import com.yinzhiwu.springmvc3.entity.type.TweetType;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.model.TweetModel;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.TweetAbbrApiView;
import com.yinzhiwu.springmvc3.model.view.TweetApiView;
import com.yinzhiwu.springmvc3.service.TweetService;

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
//		System.out.println(m.getCoverIconUrl() + "  " + m.getCoverIconUrl().length());
		TweetType type;
		try {
			type = (TweetType) baseTypeDao.get(m.getTweetTypeId());
			tweet.setTweetType(type);
		} catch (DataNotFoundException e) {
			log.warn(e.getMessage());
		}

		
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
		Tweet tweet;
		try {
			tweet = tweetDao.get(id);
			return new YiwuJson<>(new TweetApiView(tweet));
		} catch (DataNotFoundException e) {
			return new YiwuJson<>(e.getMessage());
		}
	
	}
}