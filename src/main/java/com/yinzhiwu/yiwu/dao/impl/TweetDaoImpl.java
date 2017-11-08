package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.TweetDao;
import com.yinzhiwu.yiwu.entity.Tweet;
import com.yinzhiwu.yiwu.enums.TweetType;

@Repository
public class TweetDaoImpl extends BaseDaoImpl<Tweet, Integer> implements TweetDao {


	@Override
	public List<Tweet> findByFuzzyTitle(String title) {
		return findByTypeAndFunzzyTitle(null, title);
	}

	@Override
	public List<Tweet> findByTypeAndFunzzyTitle(TweetType type, String title) {
		StringBuilder hql = new StringBuilder();
		hql.append(" FROM Tweet t1");
		hql.append(" WHERE 1=1");
		if (null != type)
			hql.append("AND t1.type= :type");
		if (title != null && !"".equals(title.trim()))
			hql.append("AND t1.title LIKE :title");
		hql.append("ORDER BY t1.createDate desc");

		List<Tweet> tweets =   getSession().createQuery(hql.toString(), Tweet.class)
				.setParameter("type", type)
				.setParameter("title", "%" + title + "%")
				.getResultList();

		return tweets;
	}

}
