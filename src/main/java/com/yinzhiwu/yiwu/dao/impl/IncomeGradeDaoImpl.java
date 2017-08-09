package com.yinzhiwu.yiwu.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.IncomeGradeDao;
import com.yinzhiwu.yiwu.entity.income.IncomeGrade;

@Repository
public class IncomeGradeDaoImpl extends BaseDaoImpl<IncomeGrade, Integer> implements IncomeGradeDao {

	@Override
	public IncomeGrade find_lowest_grade_by_income_type(Integer incomeTypeid) {
			return findByProperties(new String[] { "lowestGrade", "incomeType.id" },
					new Object[] { new Boolean(true), incomeTypeid }).get(0);
	}

}
