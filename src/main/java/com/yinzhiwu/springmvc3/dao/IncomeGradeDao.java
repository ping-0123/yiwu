package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.IncomeGrade;

public interface IncomeGradeDao extends IBaseDao<IncomeGrade, Integer> {

	IncomeGrade find_lowest_grade_by_income_type(Integer incomeTypeid);


}
