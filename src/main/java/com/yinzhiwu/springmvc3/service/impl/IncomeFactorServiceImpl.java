package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.IncomeFactorDao;
import com.yinzhiwu.springmvc3.entity.income.IncomeFactor;
import com.yinzhiwu.springmvc3.service.IncomeFactorService;

@Service
public class IncomeFactorServiceImpl extends BaseServiceImpl<IncomeFactor, Integer> implements IncomeFactorService{

	@Autowired
	public void setBaseDao(IncomeFactorDao incomeFactorDao){
		super.setBaseDao(incomeFactorDao);
	}
}
