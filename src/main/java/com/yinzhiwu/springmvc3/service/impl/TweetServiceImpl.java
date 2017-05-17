package com.yinzhiwu.springmvc3.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.TweetDao;
import com.yinzhiwu.springmvc3.entity.BaseTypeDao;
import com.yinzhiwu.springmvc3.entity.Tweet;
import com.yinzhiwu.springmvc3.entity.TweetContent;
import com.yinzhiwu.springmvc3.entity.TweetType;
import com.yinzhiwu.springmvc3.model.TweetModel;
import com.yinzhiwu.springmvc3.service.TweetService;

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
		Tweet tweet = new Tweet();
		tweet.setAuthor(m.getAuthor());
		tweet.setTitle(m.getTitle());
		TweetType type = (TweetType) baseTypeDao.get(m.getTweetTypeId());
		tweet.setTweetType(type);
		TweetContent content = new TweetContent();
		content.setContent(m.getContent().getBytes());
		tweet.setTweetContent(content);
		
		return tweetDao.save(tweet);
	}
}
