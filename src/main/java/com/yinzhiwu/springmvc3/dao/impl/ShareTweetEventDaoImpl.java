package com.yinzhiwu.springmvc3.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.yinzhiwu.springmvc3.dao.ShareTweetEventDao;
import com.yinzhiwu.springmvc3.entity.income.ShareTweetEvent;
import com.yinzhiwu.springmvc3.util.CalendarUtil;


@Repository
public class ShareTweetEventDaoImpl extends BaseDaoImpl<ShareTweetEvent, Integer> implements ShareTweetEventDao{

	@Override
	public long findDailyShareTimes(Integer distributerId, Date occurTime) {
		Assert.notNull(occurTime);
		
		StringBuilder hql =new StringBuilder("select count(*) from ShareTweetEvent where distributer.id = :distributerId");
		hql.append(" and occurTime between :start and :end");
		@SuppressWarnings("unchecked")
		List<Long> longs = (List<Long>) getHibernateTemplate().findByNamedParam(
				hql.toString(),
				new String[]{"distributerId", "start", "end"}, 
				new Object[]{distributerId, CalendarUtil.getDayBegin(occurTime).getTime(), occurTime});
		if(null == longs || longs.size() ==0)
			return 0;
		return longs.get(0);
	}

}
