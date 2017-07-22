package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.IncomeGradeDao;
import com.yinzhiwu.yiwu.entity.income.IncomeGrade;
import com.yinzhiwu.yiwu.service.IncomeGradeService;

@Service
public class IncomeGradeServiceImpl  extends BaseServiceImpl<IncomeGrade, Integer> implements IncomeGradeService{

	@Autowired
	public void setBaseDao(IncomeGradeDao incomeGradeDao){
		super.setBaseDao(incomeGradeDao);
	}
	
}
