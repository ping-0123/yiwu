package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.income.IncomeGrade;
import com.yinzhiwu.yiwu.enums.IncomeType;

public interface IncomeGradeDao extends IBaseDao<IncomeGrade, Integer> {

	IncomeGrade find_lowest_grade_by_income_type(Integer incomeTypeid);

	IncomeGrade findLowestGradeByIncomeType(IncomeType incomeType);

}
