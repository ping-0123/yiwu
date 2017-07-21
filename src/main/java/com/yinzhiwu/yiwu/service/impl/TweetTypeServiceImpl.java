package com.yinzhiwu.yiwu.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.TweetTypeDao;
import com.yinzhiwu.yiwu.entity.type.TweetType;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.TweetTypeApiView;
import com.yinzhiwu.yiwu.service.TweetTypeService;

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
