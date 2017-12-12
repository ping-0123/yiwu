package com.yinzhiwu.yiwu.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.IncomeGradeDao;
import com.yinzhiwu.yiwu.entity.income.IncomeGrade;
import com.yinzhiwu.yiwu.enums.IncomeType;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;

@Repository
public class IncomeGradeDaoImpl extends BaseDaoImpl<IncomeGrade, Integer> implements IncomeGradeDao {

	@Override
	public IncomeGrade find_lowest_grade_by_income_type(Integer incomeTypeid) {
			return findByProperties(new String[] { "lowestGrade", "incomeType.id" },
					new Object[] { new Boolean(true), incomeTypeid }).get(0);
	}

	@Override
	public IncomeGrade findLowestGradeByIncomeType(IncomeType incomeType) {
		try {
			return findOneByProperties(
					new String[]{"incomeType","lowestGrade"}, 
					new Object[]{incomeType,true});
		} catch (DataNotFoundException e) {
			throw new RuntimeException("数据有误，需要每一个类型的等级设置一个且仅设置一个最低等级",e);
		}
	}

}
