package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.IncomeGradeDao;
import com.yinzhiwu.springmvc3.entity.income.IncomeGrade;
import com.yinzhiwu.springmvc3.service.IncomeGradeService;

@Service
public class IncomeGradeServiceImpl  extends BaseServiceImpl<IncomeGrade, Integer> implements IncomeGradeService{

	@Autowired
	public void setBaseDao(IncomeGradeDao incomeGradeDao){
		super.setBaseDao(incomeGradeDao);
	}
	
}
