package com.yinzhiwu.springmvc3.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.TweetTypeDao;
import com.yinzhiwu.springmvc3.entity.type.TweetType;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.TweetTypeApiView;
import com.yinzhiwu.springmvc3.service.TweetTypeService;

import java.util.ArrayList;
import java.util.List;


@Service
public class TweetTypeServiceImpl extends BaseServiceImpl<TweetType, Integer> implements TweetTypeService{
	
	private static final Log LOG = LogFactory.getLog(TweetTypeServiceImpl.class);

	@Autowired
	private TweetTypeDao tweetTypeDao;
	
	@Autowired
	public void setBaseDao(TweetTypeDao tweetTypeDao){
		super.setBaseDao(tweetTypeDao);
	}
	
	@Override
	public YiwuJson<List<TweetTypeApiView>> findAllTweetTypes(){
		try{
			long beforeSelect = System.currentTimeMillis();
			List<TweetType> types = tweetTypeDao.findAll();
			long afterSelect = System.currentTimeMillis();
			LOG.debug("select time: " + (afterSelect-beforeSelect));
			List<TweetTypeApiView> views = new ArrayList<>();
			for (TweetType t : types) {
				views.add(new TweetTypeApiView(t));
			}
			long afterWrap = System.currentTimeMillis();
			LOG.debug("wrap Time: " +  (afterWrap-afterSelect));
			return new YiwuJson<>(views);
		}catch (DataNotFoundException e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
}
