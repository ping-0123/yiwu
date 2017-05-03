package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.ExpGradeDao;
import com.yinzhiwu.springmvc3.entity.ExpGrade;
import com.yinzhiwu.springmvc3.service.ExpGradeService;


@Repository
public class ExpGradeServiceImpl extends BaseServiceImpl<ExpGrade, Integer>  implements ExpGradeService{

	@Autowired
	private ExpGradeDao expGradeDao;
	
	@Autowired
	private  void setExpGradeDao(ExpGradeDao expGradeDao)
	{
		super.setBaseDao(expGradeDao);
	}
	
	
}
