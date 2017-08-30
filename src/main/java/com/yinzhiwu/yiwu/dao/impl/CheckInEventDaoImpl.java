package com.yinzhiwu.yiwu.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.CheckInEventDao;
import com.yinzhiwu.yiwu.entity.income.CheckInEvent;
import com.yinzhiwu.yiwu.model.view.CheckInSuccessApiView;

@Repository
public class CheckInEventDaoImpl extends BaseDaoImpl<CheckInEvent, Integer> implements CheckInEventDao {

	@Override
	public CheckInSuccessApiView findById(int eventId) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT new " + CheckInSuccessApiView.class.getName());
		hql.append("(");
		hql.append(")");
		hql.append(" FROM CheckInEvent t1");
		hql.append(" WHERE t1.id=:Id");
		
		return null;
	}

	
}
