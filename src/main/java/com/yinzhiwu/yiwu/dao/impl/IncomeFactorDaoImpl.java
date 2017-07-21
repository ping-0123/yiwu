package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.hibernate.type.IntegerType;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.IncomeFactorDao;
import com.yinzhiwu.yiwu.entity.income.IncomeFactor;
import com.yinzhiwu.yiwu.entity.type.EventType;

@Repository
public class IncomeFactorDaoImpl extends BaseDaoImpl<IncomeFactor, Integer> implements IncomeFactorDao{

	@Override
	public List<EventType> findEventTypes(int incomeTypeId) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT  DISTINCT t1.eventType");
		builder.append(" FROM IncomeFactor t1");
		builder.append(" WHERE t1.incomeType.id=:incomeTypeId");
		builder.append(" AND t1.factor <> 0");
		return getSession().createQuery(builder.toString(), EventType.class)
				.setParameter("incomeTypeId", incomeTypeId, IntegerType.INSTANCE)
				.getResultList();
	}

}
