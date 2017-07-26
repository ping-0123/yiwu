package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.TweetDao;
import com.yinzhiwu.yiwu.entity.Tweet;

@Repository
public class TweetDaoImpl extends BaseDaoImpl<Tweet, Integer> implements TweetDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Tweet> find_by_tweet_type_by_fuzzy_title(int tweetTypeId, String title) {
		StringBuilder builder = new StringBuilder("from Tweet where 1=1");
		if (tweetTypeId > 0)
			builder.append("and tweetType.id=" + tweetTypeId);
		if (title != null && !title.equals(""))
			builder.append("and title like '%" + title + "%'");

		return (List<Tweet>) getHibernateTemplate().find(builder.toString());
		// return getSession().createQuery(builder.toString(),
		// Tweet.class).list();
	}

}
