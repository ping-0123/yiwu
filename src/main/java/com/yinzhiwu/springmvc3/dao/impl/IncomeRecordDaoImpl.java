package com.yinzhiwu.springmvc3.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.IncomeRecordDao;
import com.yinzhiwu.springmvc3.entity.income.IncomeRecord;

@Repository
public class IncomeRecordDaoImpl extends BaseDaoImpl<IncomeRecord, Integer> implements IncomeRecordDao {

	@Override
	public int findCountByIncomeTypesByBeneficiary(int benificiaryId, int[] incomeTypeIds) {
		if(incomeTypeIds.length == 0) return 0;
		List<Integer> ints = new ArrayList<>();
		for (int i : incomeTypeIds) {
			ints.add(i);
		}
		
		StringBuilder builder = new StringBuilder("select count(*) from IncomeRecord t1 ");
		builder.append("where t1.benificiary.id = :benificiaryId and t1.incomeType.id in :incomeTypeIds");
		@SuppressWarnings("unchecked")
		List<Long> counts = (List<Long>) getHibernateTemplate().findByNamedParam(builder.toString(),
				new String[]{"benificiaryId", "incomeTypeIds"}, 
				new Object[]{benificiaryId, ints});
		return counts.get(0).intValue();
	}

}
