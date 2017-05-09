package com.yinzhiwu.springmvc3.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.CapitalAccountDao;
import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.dao.MoneyRecordDao;
import com.yinzhiwu.springmvc3.dao.RecordTypeDao;
import com.yinzhiwu.springmvc3.entity.BrokerageRecord;
import com.yinzhiwu.springmvc3.entity.BrokerageRecordType;
import com.yinzhiwu.springmvc3.entity.CapitalAccount;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.FundsRecord;
import com.yinzhiwu.springmvc3.entity.FundsRecordType;
import com.yinzhiwu.springmvc3.entity.MoneyRecord;
import com.yinzhiwu.springmvc3.entity.MoneyRecordType;
import com.yinzhiwu.springmvc3.entity.WithDrawRecord;
import com.yinzhiwu.springmvc3.enums.MoneyRecordCategory;
import com.yinzhiwu.springmvc3.model.MoneyRecordApiView;
import com.yinzhiwu.springmvc3.model.WithDrawModel;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.service.MoneyRecordService;
import com.yinzhiwu.springmvc3.util.MoneyRecordCategoryUtil;

@Service
public class MoneyRecordServiceImpl extends BaseServiceImpl<MoneyRecord, Integer> implements MoneyRecordService{
	
	@Autowired
	private MoneyRecordDao moneyRecordDao;
	
	@Autowired
	private RecordTypeDao recordTypeDao;
	
	@Autowired
	private DistributerDao distributerDao;
	
	
	@Autowired
	private CapitalAccountDao capitalAccountDao;
	
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
		beneficiary.setBrokerage(brokerageRecord.getCurrentBrokerage());
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

	private void saveWithDrawRecord(Distributer beneficiary, Distributer contributor, float value,
			BrokerageRecordType type, CapitalAccount account) 
	{
		WithDrawRecord record = new WithDrawRecord(beneficiary, contributor, value, type);
		record.setAccount(account);
		moneyRecordDao.save(record);
		beneficiary.setBrokerage(record.getCurrentBrokerage());
		distributerDao.update(beneficiary);
		
	}
	
	@Override
	public YiwuJson<Integer> findCountByDistributerid(int distributerId) {
		int count =  moneyRecordDao.findCountByBeneficiatyId(distributerId);
		return new YiwuJson<Integer>(count);
	}

	@Override
	public YiwuJson<List<MoneyRecordApiView>> findList(int benificiaryId, MoneyRecordCategory category) {
		List<Integer> typeIds  = MoneyRecordCategoryUtil.toMoneyRecordTypeIds(category);
		List<MoneyRecord> moneyRecords = moneyRecordDao.findByTypesByBeneficiaryId(benificiaryId,typeIds);
		List<MoneyRecordApiView> views = new ArrayList<MoneyRecordApiView>();
		for (MoneyRecord m : moneyRecords) {
			views.add(new MoneyRecordApiView(m));
		}
		return new YiwuJson<List<MoneyRecordApiView>>(views);
	}

	@Override
	public boolean withdraw(WithDrawModel m) {
		BrokerageRecordType type = recordTypeDao.getWithDrawMoneyRecordType();
		Distributer beneficiary = distributerDao.get(m.getDistributerId());
		Distributer contributor = beneficiary;
		float value = m.getAmount();
		CapitalAccount account = capitalAccountDao.get(m.getAccountid());
		saveWithDrawRecord(beneficiary,contributor,value,type,account);
		return true;
	}


}
