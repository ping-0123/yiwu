package com.yinzhiwu.yiwu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.TweetDao;
import com.yinzhiwu.yiwu.entity.Tweet;

@Repository
public class TweetDaoImpl extends BaseDaoImpl<Tweet, Integer> implements TweetDao {

	@Override
	public List<Tweet> find_by_tweet_type_by_fuzzy_title(int tweetTypeId, String title) {
		StringBuilder hql = new StringBuilder();
		hql.append(" FROM Tweet t1");
		hql.append(" WHERE 1=1");
		if (tweetTypeId > 0)
			hql.append("AND t1.tweetType.id=" + tweetTypeId);
		if (title != null && !"".equals(title))
			hql.append("AND t1.title LIKE '%" + title + "%'");
		hql.append("ORDER BY t1.createDate desc");

		List<Tweet> tweets =   getSession().createQuery(hql.toString(), Tweet.class)
				.getResultList();
		if(tweets == null ) return new ArrayList<>();
		return tweets;
	}

}
