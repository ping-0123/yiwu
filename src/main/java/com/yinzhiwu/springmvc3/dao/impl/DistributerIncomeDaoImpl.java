package com.yinzhiwu.springmvc3.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.DistributerIncomeDao;
import com.yinzhiwu.springmvc3.entity.income.DistributerIncome;
import com.yinzhiwu.springmvc3.entity.type.IncomeType;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;

@Repository
public class DistributerIncomeDaoImpl extends BaseDaoImpl<DistributerIncome, Integer> implements DistributerIncomeDao {

	@Override
	public DistributerIncome find_by_distributer_by_income_type(Integer distributerId, Integer incomeTypeId)
			throws DataNotFoundException {
		return findByProperties(
				new String[]{"distributer.id", "incomeType.id"}, 
				new Object[]{distributerId, incomeTypeId}).get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Float get_beat_rate(IncomeType incomeType,float incomeValue) {
		int sumCount = findCountByProperty("incomeType.id", incomeType.getId());
		if(sumCount == 0) return 0f;
		
		String hql = "select count(*) from DistributerIncome where incomeType.id=:incomeTypeId and income<=:income";
		 List<Long> counts = (List<Long>) getHibernateTemplate().findByNamedParam(
				hql, 
				new String[]{"incomeTypeId", "income"}, 
				new Object[]{incomeType.getId(),incomeValue});
		 
		return counts.get(0)/(float)sumCount;
	}

}
