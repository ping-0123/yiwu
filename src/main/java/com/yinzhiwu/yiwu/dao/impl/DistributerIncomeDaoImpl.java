package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.DistributerIncomeDao;
import com.yinzhiwu.yiwu.entity.income.DistributerIncome;
import com.yinzhiwu.yiwu.enums.IncomeType;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;

@Repository
public class DistributerIncomeDaoImpl extends BaseDaoImpl<DistributerIncome, Integer> implements DistributerIncomeDao {

	
	@Override
	public Float calculateBeatRatio(com.yinzhiwu.yiwu.enums.IncomeType type, Float value) {
		long sumCount = findCountByProperty("type", type);
		if(0==sumCount) return 0f;  //新环境可能会出现这种情况
		
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT COUNT(1)");
		hql.append(" FROM DistributerIncome t1");
		hql.append(" WHERE t1.type=:type");
		hql.append(" AND t1.value<=:value");
		long count = getSession().createQuery(hql.toString(), Long.class)
				.setParameter("type", type)
				.setParameter("value", value)
				.getSingleResult();
		
		return count/(float) sumCount;
	}


	@Override
	public List<DistributerIncome> getTopN(IncomeType type, int topN) {
		if (topN < 0)
			throw new IllegalIdentifierException("topN should be positive number");
		
		StringBuilder hql = new StringBuilder();
		hql.append("FROM DistributerIncome");
		hql.append(" WHERE type =:type");
		hql.append(" ORDER BY accumulativeValue DESC");
		
		return getSession().createQuery(hql.toString(), DistributerIncome.class)
				.setParameter("type", type)
				.setMaxResults(topN)
				.getResultList();
	}


	@Override
	public List<DistributerIncome> findBrokerageIncomes() {
		return findByProperty("type",IncomeType.BROKERAGE);
	}


	@Override
	public DistributerIncome findbyDistributerIdAndIncomeType(Integer distributerId, IncomeType type) throws DataNotFoundException {
		return findOneByProperties(
				new String[]{"distributer.id","type" },
				new Object[]{distributerId, type});
	}


}
