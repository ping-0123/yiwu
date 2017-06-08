package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.DistributerIncomeDao;
import com.yinzhiwu.springmvc3.dao.IncomeGradeDao;
import com.yinzhiwu.springmvc3.entity.DistributerIncome;
import com.yinzhiwu.springmvc3.entity.IncomeRecord;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.service.DistributerIncomeService;

@Service
public class DistributerIncomeServiceImpl extends BaseServiceImpl<DistributerIncome, Integer> implements DistributerIncomeService {

	@Autowired
	private DistributerIncomeDao dIncomeDao;
	
	@Autowired
	private IncomeGradeDao incomeGradeDao;
	
	@Autowired
	public void setBaseDao(DistributerIncomeDao distributerIncomeDao){
		super.setBaseDao(distributerIncomeDao);
	}

	@Override
	public void update_by_record(IncomeRecord record) {
		
		DistributerIncome dIncome;
		try {
			 dIncome = dIncomeDao.find_by_distributer_by_income_type(
					record.getBenificiary().getId(),
					record.getIncomeType().getId());
		} catch (DataNotFoundException e) {
			dIncome = new DistributerIncome(
					record.getBenificiary(), 
					record.getIncomeType(), 
					incomeGradeDao.find_lowest_grade_by_income_type(record.getIncomeType().getId()));
		}
		
		dIncome.setIncome(dIncome.getIncome() + record.getIncomeValue());
		if(record.getIncomeValue() >0)
			dIncome.setAccumulativeIncome(dIncome.getAccumulativeIncome() + record.getIncomeValue());
		
		dIncomeDao.saveOrUpdate(dIncome);
	}
	
	
}
