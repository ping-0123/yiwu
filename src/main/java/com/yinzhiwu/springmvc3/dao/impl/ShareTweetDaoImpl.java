package com.yinzhiwu.springmvc3.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.ShareTweetDao;
import com.yinzhiwu.springmvc3.entity.ShareTweet;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;


@Repository
public class ShareTweetDaoImpl 
	extends BaseDaoImpl<ShareTweet,Integer> 
	implements ShareTweetDao {
	
	private static Log LOG  = LogFactory.getLog(ShareTweetDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public int findCountBySharerByDate(int distributerId, Date date) {
		String hql = "select count(*) from ShareTweet where sharer.id=:sharerId and shareDate >= :startDate";
		List<Long> list = (List<Long>) getHibernateTemplate().findByNamedParam(
				hql, 
				new String[]{"sharerId", "startDate"},
				new Object[]{distributerId,date});
		LOG.info("date..." + date);
		LOG.info(list.get(0).intValue());
		return list.get(0).intValue();
	}

	@Override
	public List<ShareTweet> findBySharerId(int sharerId) {
		try{
			return findByProperty("sharer.id", sharerId);
		}catch (DataNotFoundException e) {
			LOG.warn(e);
			return new ArrayList<>();
		}
	}

	@Override
	public List<ShareTweet> findBySharerIds(List<Integer> subordiateIds) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
