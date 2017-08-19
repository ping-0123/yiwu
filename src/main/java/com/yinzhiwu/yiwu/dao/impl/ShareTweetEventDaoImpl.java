package com.yinzhiwu.yiwu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.ShareTweetEventDao;
import com.yinzhiwu.yiwu.entity.income.ShareTweetEvent;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.util.CalendarUtil;

@Repository
public class ShareTweetEventDaoImpl extends BaseDaoImpl<ShareTweetEvent, Integer> implements ShareTweetEventDao {

	@Override
	public long findDailyShareTimes(Integer distributerId, Date occurTime) {
		Assert.notNull(occurTime);

		StringBuilder hql = new StringBuilder(
				"select count(*) from ShareTweetEvent where distributer.id = :distributerId");
		hql.append(" and occurTime between :start and :end");
		@SuppressWarnings("unchecked")
		List<Long> longs = (List<Long>) getHibernateTemplate().findByNamedParam(hql.toString(),
				new String[] { "distributerId", "start", "end" },
				new Object[] { distributerId, CalendarUtil.getDayBegin(occurTime).getTime(), occurTime });
		if (null == longs || longs.size() == 0)
			return 0;
		return longs.get(0);
	}

	@Override
	public int findShareTweetTimes(int distributerId) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(1)");
		builder.append(" FROM ShareTweetEvent T1");
		builder.append(" WHERE T1.distributer.id=:distributerId");
		builder.append(" AND T1.type.id IN :eventTypes");
		List<Integer> typeIds = new ArrayList<>();
		typeIds.add(EventType.SHARE_TWEET_BY_WECHAT_AFTER_THREE_TIMES_PER_DAY.getId());
		typeIds.add(EventType.SHARE_TWEET_BY_WECHAT_FIRST_THREE_TIMES_PER_DAY.getId());
		return findCount(builder.toString(),
				new String[]{"distributerId","eventTypes"}, 
				new Object[]{distributerId, typeIds})
				.intValue();
	}

}
