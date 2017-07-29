package com.yinzhiwu.yiwu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.BaseTypeDao;
import com.yinzhiwu.yiwu.dao.TweetDao;
import com.yinzhiwu.yiwu.entity.Tweet;
import com.yinzhiwu.yiwu.entity.type.TweetType;
import com.yinzhiwu.yiwu.model.TweetModel;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.TweetAbbrApiView;
import com.yinzhiwu.yiwu.model.view.TweetApiView;
import com.yinzhiwu.yiwu.service.TweetService;

@Service
public class TweetServiceImpl extends BaseServiceImpl<Tweet, Integer> implements TweetService {

	private static Log log = LogFactory.getLog(TweetServiceImpl.class);

	@Autowired
	private TweetDao tweetDao;
	@Autowired
	private BaseTypeDao baseTypeDao;
	@Autowired private FileService fileService;
	
	@Autowired
	public void setBaseDao(TweetDao tweetDao) {
		super.setBaseDao(tweetDao);
	}

	@Override
	public int save(TweetModel m) {
		Tweet tweet = new Tweet(m);
		// System.out.println(m.getCoverIconUrl() + " " +
		// m.getCoverIconUrl().length());
		TweetType type;
		type = (TweetType) baseTypeDao.get(m.getTweetTypeId());
		tweet.setTweetType(type);

		return tweetDao.save(tweet);
	}

	@Override
	public YiwuJson<List<TweetAbbrApiView>> findByTypeByFuzzyTitle(int tweetTypeId, String title) {
		List<Tweet> tweets = tweetDao.find_by_tweet_type_by_fuzzy_title(tweetTypeId, title);
		List<TweetAbbrApiView> views = new ArrayList<>();
		for (Tweet t : tweets) {
			views.add(_wrapToAbbrApiView(t));
		}
		return new YiwuJson<>(views);
	}

	private TweetAbbrApiView _wrapToAbbrApiView(Tweet t) {
		TweetAbbrApiView view = new TweetAbbrApiView(t);
		view.setCoverIconUrl(fileService.getFileUrl(t.getCoverImage()));
		return view;
	}

	@Override
	public YiwuJson<TweetApiView> findById(int id) {
		Tweet tweet;
		tweet = tweetDao.get(id);
		return new YiwuJson<>(new TweetApiView(tweet));
	}
	
}
