package com.yinzhiwu.springmvc3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.springmvc3.dao.DistributerIncomeDao;
import com.yinzhiwu.springmvc3.dao.IncomeFactorDao;
import com.yinzhiwu.springmvc3.dao.IncomeRecordDao;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.income.IncomeEvent;
import com.yinzhiwu.springmvc3.entity.income.IncomeFactor;
import com.yinzhiwu.springmvc3.entity.income.IncomeRecord;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.service.DistributerIncomeService;
import com.yinzhiwu.springmvc3.service.IncomeRecordService;
import com.yinzhiwu.springmvc3.service.MessageService;

@Service
public class IncomeRecordServiceImpl  extends BaseServiceImpl<IncomeRecord, Integer> implements IncomeRecordService{
	
	@Autowired
	private DistributerIncomeService dIncomeService;
	
	@Autowired DistributerIncomeDao dIncomeDao;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private IncomeFactorDao incomeFactorDao;
	
	@Autowired
	public void setBaseDao(IncomeRecordDao incomeRecordDao){
		super.setBaseDao(incomeRecordDao);
	}
	
	@Override
	public Integer save(IncomeRecord incomeRecord){
		Assert.notNull(incomeRecord);
		Assert.notNull(incomeRecord.getBenificiary());
		Assert.notNull(incomeRecord.getIncomeType());
		
		incomeRecord.setCurrentValue(dIncomeDao.findCurrentValue(
				incomeRecord.getBenificiary().getId(), incomeRecord.getIncomeType().getId()));
		super.save(incomeRecord);
		dIncomeService.update_by_record(incomeRecord);
		messageService.save_by_record(incomeRecord);
		return incomeRecord.getId();
	}

	@Override
	public void save_records_produced_by_event(IncomeEvent event) {
		List<IncomeFactor> factors;
		try {
			factors = incomeFactorDao.findByProperty("eventType.id", event.getType().getId());
		} catch (DataNotFoundException e) {
			logger.info(e.getMessage());
			return;
		}
		for (IncomeFactor factor : factors) {
			Distributer benificiary = factor.getRelation().getRelativeDistributer(event.getDistributer());
			if(benificiary != null && factor.getFactor() != 0f && event.getParam() != 0){
				IncomeRecord record = new IncomeRecord(event, factor, benificiary);
				this.save(record);
			}
		}
	}
}
