package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.dao.MoneyRecordDao;
import com.yinzhiwu.springmvc3.dao.RecordTypeDao;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.FundsRecord;
import com.yinzhiwu.springmvc3.entity.FundsRecordType;
import com.yinzhiwu.springmvc3.entity.MoneyRecord;
import com.yinzhiwu.springmvc3.service.MoneyRecordService;

@Service
public class MoneyRecordServiceImpl extends BaseServiceImpl<MoneyRecord, Integer> implements MoneyRecordService{
	
	@Autowired
	private MoneyRecordDao MoneyRecordDao;
	
	@Autowired
	private RecordTypeDao recordTypeDao;
	
	@Autowired
	private DistributerDao distributerDao;
	
	@Autowired
	private void setMoneyRecordDao(MoneyRecordDao moneyRecordDao)
	{
		super.setBaseDao(moneyRecordDao);
	}
	
	@Override
	public void saveRegisterFundsRecord(Distributer beneficiary, Distributer contributor) {
		FundsRecordType fundsRecordType = recordTypeDao.findRegisterFundsRecordType();
		FundsRecord fundsRecord = new FundsRecord(beneficiary,contributor,1,fundsRecordType);
		MoneyRecordDao.save(fundsRecord);
		
		beneficiary.setFunds(fundsRecord.getCurrentFunds());
		distributerDao.update(beneficiary);
	}

}
