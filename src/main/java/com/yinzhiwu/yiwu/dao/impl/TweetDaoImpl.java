package com.yinzhiwu.yiwu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;
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
		List<String> propeties = new ArrayList<String>();
		List<Object> values = new ArrayList<>();
		hql.append(" FROM Tweet t1");
		hql.append(" WHERE 1=1");
		if (null != type){
			hql.append(" AND t1.type= :type");
			propeties.add("type");
			values.add(type);
		}
		if (title != null && !"".equals(title.trim())){
			hql.append(" AND t1.title LIKE :title");
			propeties.add("title");
			values.add("%" + title + "%");
		}
		hql.append(" ORDER BY t1.createTime desc");

	   Query<Tweet> query = getSession().createQuery(hql.toString(), Tweet.class);
   		for (int i=0; i<propeties.size(); i++) {
			query.setParameter(propeties.get(i), values.get(i));
		}
		   		
   		return query.getResultList();
   		

	}

}
