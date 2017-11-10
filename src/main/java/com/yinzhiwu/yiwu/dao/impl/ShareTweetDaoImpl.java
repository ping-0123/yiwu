package com.yinzhiwu.yiwu.dao.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.ShareTweetDao;
import com.yinzhiwu.yiwu.entity.ShareTweet;
import com.yinzhiwu.yiwu.util.CalendarUtil;

/**
*@Author ping
*@Time  创建时间:2017年11月9日下午4:57:24
*
*/

@Repository
public class ShareTweetDaoImpl extends BaseDaoImpl<ShareTweet,Integer> implements ShareTweetDao{

	@Override
	public int getSumSharedTimesOfCurrentDay(Integer distributerId) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date start = CalendarUtil.getDayBegin(calendar).getTime();
		Date end = CalendarUtil.getDayEnd(calendar).getTime();
		
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT COUNT(1)");
		hql.append(" FROM ShareTweet");
		hql.append(" WHERE distributer.id =:distributerId");
		hql.append(" AND date BETWEEN :start AND :end");
		
		return getSession().createQuery(hql.toString(), Long.class)
				.setParameter("distributerId", distributerId)
				.setParameter("start", start)
				.setParameter("end", end)
				.getSingleResult()
				.intValue();
	}

	@Override
	public Long findCountByDistributerId(Integer distributerId) {
		return findCountByProperty("distributer.id", distributerId);
	}

}
