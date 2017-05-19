package com.yinzhiwu.springmvc3.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.ShareTweetDao;
import com.yinzhiwu.springmvc3.entity.ShareTweet;


@Repository
public class ShareTweetDaoImpl 
	extends BaseDaoImpl<ShareTweet,Integer> 
	implements ShareTweetDao {

	@SuppressWarnings("unchecked")
	@Override
	public int findCountBySharerByDate(int distributerId, Date date) {
		String hql = "select count(*) from ShareTweet where sharer.id=:sharerId and shareDate >= :startDate";
		List<Long> list = (List<Long>) getHibernateTemplate().findByNamedParam(
				hql, 
				new String[]{"sharerId", "startDate"},
				new Object[]{distributerId,date});
		return list.get(0).intValue();
	}
	

}
