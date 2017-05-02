package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.ExpGrade;

public interface ExpGradeDao extends IBaseDao<ExpGrade, Integer> {

	public ExpGrade findLowestGrade();
}
