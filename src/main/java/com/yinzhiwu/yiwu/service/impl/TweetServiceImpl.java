package com.yinzhiwu.yiwu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.TweetDao;
import com.yinzhiwu.yiwu.entity.Tweet;
import com.yinzhiwu.yiwu.service.TweetService;

@Service
public class TweetServiceImpl extends BaseServiceImpl<Tweet, Integer> implements TweetService {

	@Autowired private TweetDao tweetDao;
	
	@Autowired
	public void setBaseDao(TweetDao tweetDao) {
		super.setBaseDao(tweetDao);
	}

	@Override
	public List<Tweet> findByFuzzyTitle(String title) {
		return tweetDao.findByFuzzyTitle(title);
	}

	@Override
	public List<Tweet> findByTypeAndFunzzyTitle(com.yinzhiwu.yiwu.enums.TweetType type, String title) {
		return tweetDao.findByTypeAndFunzzyTitle(type, title);
	}



	
}
