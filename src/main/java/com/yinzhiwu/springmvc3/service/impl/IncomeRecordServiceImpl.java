package com.yinzhiwu.springmvc3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.IncomeRecordDao;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.income.IncomeEvent;
import com.yinzhiwu.springmvc3.entity.income.IncomeFactor;
import com.yinzhiwu.springmvc3.entity.income.IncomeRecord;
import com.yinzhiwu.springmvc3.service.DistributerIncomeService;
import com.yinzhiwu.springmvc3.service.IncomeRecordService;
import com.yinzhiwu.springmvc3.service.MessageService;

@Service
public class IncomeRecordServiceImpl  extends BaseServiceImpl<IncomeRecord, Integer> implements IncomeRecordService{
	
	@Autowired
	private DistributerIncomeService dIncomeService;
	
	@Autowired
	private MessageService messageService;
	
	
	@Autowired
	public void setBaseDao(IncomeRecordDao incomeRecordDao){
		super.setBaseDao(incomeRecordDao);
	}
	
	@Override
	public Integer save(IncomeRecord incomeRecord){
		super.save(incomeRecord);
		dIncomeService.update_by_record(incomeRecord);
		messageService.save_by_record(incomeRecord);
		return incomeRecord.getId();
	}

	@Override
	public void save_records_produced_by_event(IncomeEvent event) {
		List<IncomeFactor> factors = event.getType().getIncomeFactors();
		for (IncomeFactor factor : factors) {
			Distributer benificiary = factor.getRelation().getRelativeDistributer(event.getDistributer());
			if(benificiary != null && factor.getFactor() != 0f && event.getParam() != 0){
				IncomeRecord record = new IncomeRecord(event, factor, benificiary);
				this.save(record);
			}
		}
	}
}
