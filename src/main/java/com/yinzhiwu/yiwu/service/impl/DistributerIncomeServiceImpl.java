package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.DistributerIncomeDao;
import com.yinzhiwu.yiwu.dao.IncomeGradeDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.Message;
import com.yinzhiwu.yiwu.entity.income.DistributerIncome;
import com.yinzhiwu.yiwu.entity.income.IncomeRecord;
import com.yinzhiwu.yiwu.enums.IncomeType;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.service.DistributerIncomeService;
import com.yinzhiwu.yiwu.service.MessageService;
import com.yinzhiwu.yiwu.util.MessageTemplate;

@Service
public class DistributerIncomeServiceImpl extends BaseServiceImpl<DistributerIncome, Integer>
		implements DistributerIncomeService {

	@Autowired private MessageService messageService;

	@Autowired private IncomeGradeDao incomeGradeDao;
	@Autowired private DistributerIncomeDao incomeDao;

	@Autowired
	public void setBaseDao(DistributerIncomeDao distributerIncomeDao) {
		super.setBaseDao(distributerIncomeDao);
	}


	@Override
	public void updateIncome(IncomeRecord record) {
		Assert.notNull(record);
		
		DistributerIncome income = record.getBenificiary().getDistributerIncome(record.getIncomeType());
		if(null == income){
			income = new DistributerIncome();
			income.init();
			income.setDistributer(record.getBenificiary());
			income.setType(record.getIncomeType());
			income.setGrade(incomeGradeDao.findLowestGradeByIncomeType(record.getIncomeType()));
		}
		
		//设置收益
		income.setValue(income.getValue() + record.getIncomeValue());
		//升级
		if(income.getValue() >= income.getGrade().getUpgradeNeededValue()
				&& !income.getGrade().getHighesGrade()){
			income.setGrade(income.getGrade().getNextGrade());
			//  升级产生消息
			messageService.save(new Message(income.getDistributer(), 
					MessageTemplate.generate_update_grade_message(
							income.getType().getName(),income.getGrade().getName() )));
		}
		//设置累积收益
		if(record.getIncomeValue()>0)
			income.setAccumulativeValue(income.getAccumulativeValue() + record.getIncomeValue());
		
		//保存
		saveOrUpdate(income);
	}


	@Override
	public float calculateBeatRatio(IncomeType type, float value) {
		return incomeDao.calculateBeatRatio(type, value);
	}


	@Override
	public DistributerIncome getIncome(Distributer distributer, IncomeType type) {
		try {
			return incomeDao.findbyDistributerIdAndIncomeType(distributer.getId(), type);
		} catch (DataNotFoundException e) {
			return null;
		}
	}

}
