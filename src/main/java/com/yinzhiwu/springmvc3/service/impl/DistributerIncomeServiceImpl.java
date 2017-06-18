package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.springmvc3.dao.DistributerIncomeDao;
import com.yinzhiwu.springmvc3.dao.IncomeGradeDao;
import com.yinzhiwu.springmvc3.entity.income.DistributerIncome;
import com.yinzhiwu.springmvc3.entity.income.IncomeRecord;
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
		Assert.notNull(record);
		
		/**
		 * get the influenced distributer's income by the record
		 */
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
		
		
		/**
		 * update distributer's income value
		 */
		dIncome.setIncome(dIncome.getIncome() + record.getIncomeValue());
		
		/**
		 * update distributer's income grade
		 */
		try{
			if(dIncome.getIncome() >=dIncome.getIncomeGrade().getNextGrade().getUpgradeNeededValue())
				dIncome.setIncomeGrade(dIncome.getIncomeGrade().getNextGrade());
		}catch (NullPointerException e) {
			logger.info("distributer: " + dIncome.getDistributer().getId() + "'s " 
					+ dIncome.getIncomeType().getName() + " income grade is the upest grade");
		}
		
		/**
		 * update distributer's accumulative income value
		 */
		if(record.getIncomeValue() >0)
			dIncome.setAccumulativeIncome(dIncome.getAccumulativeIncome() + record.getIncomeValue());
		
		/**
		 * persistent the distributer's income
		 */
		dIncomeDao.saveOrUpdate(dIncome);
	}
	
	
}
