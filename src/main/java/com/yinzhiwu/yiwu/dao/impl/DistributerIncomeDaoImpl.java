package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.DistributerIncomeDao;
import com.yinzhiwu.yiwu.entity.income.DistributerIncome;
import com.yinzhiwu.yiwu.entity.type.IncomeType;

@Repository
public class DistributerIncomeDaoImpl extends BaseDaoImpl<DistributerIncome, Integer> implements DistributerIncomeDao {

	@Override
	public DistributerIncome find_by_distributer_by_income_type(Integer distributerId, Integer incomeTypeId) {
		
		List<DistributerIncome> incomes = findByProperties(
				new String[] { "distributer.id", "incomeType.id" },
				new Object[] { distributerId, incomeTypeId });
		if(incomes.size() > 0)
			return incomes.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Float get_beat_rate(IncomeType incomeType, float incomeValue) {
		int sumCount = findCountByProperty("incomeType.id", incomeType.getId());
		if (sumCount == 0)
			return 0f;

		String hql = "select count(*) from DistributerIncome where incomeType.id=:incomeTypeId and income<=:income";
		List<Long> counts = (List<Long>) getHibernateTemplate().findByNamedParam(hql,
				new String[] { "incomeTypeId", "income" }, new Object[] { incomeType.getId(), incomeValue });

		return counts.get(0) / (float) sumCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Float findCurrentValue(Integer distributerId, Integer incomeTypeId) {
		String hql = "select income from DistributerIncome where distributer.id=:distributerId and incomeType.id = :incomeTypeId";
		List<Float> floats = (List<Float>) getHibernateTemplate().findByNamedParam(hql,
				new String[] { "distributerId", "incomeTypeId" }, new Object[] { distributerId, incomeTypeId });
		if (floats.size() == 0 || null == floats)
			return 0f;
		return floats.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DistributerIncome> getTopN(int incomeTypeId, int topN) {
		if (topN < 0)
			throw new IllegalIdentifierException("topN should be positive number");

		StringBuilder builder = new StringBuilder();
		builder.append("FROM DistributerIncome t1");
		builder.append(" WHERE t1.incomeType.id=:incomeTypeId");
		builder.append(" ORDER BY t1.accumulativeIncome DESC");
		getHibernateTemplate().setMaxResults(topN);
		return (List<DistributerIncome>) getHibernateTemplate().findByNamedParam(builder.toString(), "incomeTypeId",
				incomeTypeId);
	}

}
