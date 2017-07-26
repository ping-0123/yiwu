package com.yinzhiwu.yiwu.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.IncomeGradeDao;
import com.yinzhiwu.yiwu.entity.income.IncomeGrade;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

@Repository
public class IncomeGradeDaoImpl extends BaseDaoImpl<IncomeGrade, Integer> implements IncomeGradeDao {

	@Override
	public IncomeGrade find_lowest_grade_by_income_type(Integer incomeTypeid) {
		try {
			return findByProperties(new String[] { "lowestGrade", "incomeType.id" },
					new Object[] { new Boolean(true), incomeTypeid }).get(0);
		} catch (DataNotFoundException e) {
			logger.error("请输入经验等级基础数据");
			return null;
		}
	}

}
