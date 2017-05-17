package com.yinzhiwu.springmvc3.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.TweetDao;
import com.yinzhiwu.springmvc3.entity.Tweet;

@Repository
public class TweetDaoImpl extends BaseDaoImpl<Tweet, Integer>
	implements TweetDao
{

}
