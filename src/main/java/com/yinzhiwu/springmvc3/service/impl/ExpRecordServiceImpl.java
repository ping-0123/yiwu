package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.dao.ExpRecordDao;
import com.yinzhiwu.springmvc3.dao.ExpRecordTypeDao;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.ExpRecord;
import com.yinzhiwu.springmvc3.entity.RecordType;
import com.yinzhiwu.springmvc3.service.ExpRecordService;

@Service
public class ExpRecordServiceImpl extends BaseServiceImpl<ExpRecord, Integer> implements ExpRecordService {
	
	@Autowired
	private ExpRecordDao expRecordDao;
	
	@Autowired
	private ExpRecordTypeDao expRecordTypeDao;
	
	@Autowired
	private DistributerDao distrituterDao;

	@Autowired
	public void setExpRecordDao(ExpRecordDao expRecordDao)
	{
		super.setBaseDao(expRecordDao);
	}

	@Override
	public void saveSubordinateRegisterExpRecord(Distributer beneficiaty, Distributer subordinate) {
		RecordType recordType = expRecordTypeDao.findSubordinateRegisterExpRecordType();
		ExpRecord expRecord = new ExpRecord(beneficiaty,subordinate,1,recordType);
		expRecordDao.save(expRecord);
		
		//更新benificiary exp记录
		beneficiaty.setExp(expRecord.getCurrentExp());
		distrituterDao.update(beneficiaty);
	}

	@Override
	public void saveSecondaryRegisterExpRecord(Distributer beneficiaty, Distributer secondary) {
		RecordType recordType = expRecordTypeDao.findSecondaryRegisterExpRecordType();
		ExpRecord expRecord = new ExpRecord(beneficiaty,secondary,1,recordType);
		expRecordDao.save(expRecord);
		
		//更新benificiary exp记录
		beneficiaty.setExp(expRecord.getCurrentExp());
		distrituterDao.update(beneficiaty);
	}
}
