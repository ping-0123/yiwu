package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.dao.MoneyRecordDao;
import com.yinzhiwu.springmvc3.dao.RecordTypeDao;
import com.yinzhiwu.springmvc3.entity.BrokerageRecord;
import com.yinzhiwu.springmvc3.entity.BrokerageRecordType;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.FundsRecord;
import com.yinzhiwu.springmvc3.entity.FundsRecordType;
import com.yinzhiwu.springmvc3.entity.MoneyRecord;
import com.yinzhiwu.springmvc3.entity.MoneyRecordType;
import com.yinzhiwu.springmvc3.service.MoneyRecordService;

@Service
public class MoneyRecordServiceImpl extends BaseServiceImpl<MoneyRecord, Integer> implements MoneyRecordService{
	
	@Autowired
	private MoneyRecordDao moneyRecordDao;
	
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
		saveFundsRecord(beneficiary, contributor, 1, fundsRecordType);
	}

	@Override
	public void saveMoneyRecord(Distributer beneficiary,Distributer contributor, float value, MoneyRecordType type )
	{
		if(type instanceof BrokerageRecordType)
			saveBrokerageRecord(beneficiary, contributor, value, (BrokerageRecordType)type);
		else if(type instanceof FundsRecordType)
			saveFundsRecord(beneficiary, contributor, value, (FundsRecordType) type);
	}
	
	
	
	private void saveBrokerageRecord(Distributer beneficiary,Distributer contributor, float value, BrokerageRecordType type )
	{
		BrokerageRecord brokerageRecord = new BrokerageRecord(beneficiary, contributor, value, type);
		moneyRecordDao.save(brokerageRecord);
		if(brokerageRecord.getIncome() >0)
			beneficiary.setAccumulativeBrokerage(beneficiary.getAccumulativeBrokerage() + brokerageRecord.getIncome());
		distributerDao.update(beneficiary);
	}
	
	
	
	private void saveFundsRecord(Distributer beneficiary,Distributer contributor, float value, FundsRecordType type )
	{
		FundsRecord fundsRecord = new FundsRecord(beneficiary, contributor, value, type);
		moneyRecordDao.save(fundsRecord);
		
		if(fundsRecord.getIncome() > 0)
			beneficiary.setAccumulativeFunds(beneficiary.getAccumulativeFunds() + fundsRecord.getIncome());
		beneficiary.setFunds(fundsRecord.getCurrentFunds());
		distributerDao.update(beneficiary);
	}
}
