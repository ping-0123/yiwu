package com.yinzhiwu.springmvc3.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.RecordTypeDao;
import com.yinzhiwu.springmvc3.entity.BrokerageRecordType;
import com.yinzhiwu.springmvc3.entity.ExpRecordType;
import com.yinzhiwu.springmvc3.entity.FundsRecordType;
import com.yinzhiwu.springmvc3.entity.RecordType;

@Repository
public class RecordTypeDaoImpl extends BaseDaoImpl<RecordType,Integer> implements RecordTypeDao{

	@Override
	public FundsRecordType findRegisterFundsRecordType() {
		return (FundsRecordType) get(17000004);
	}

	@Override
	public ExpRecordType findSubordinateRegisterExpRecordType() {
		return (ExpRecordType) get(17000001);
	}

	@Override
	public ExpRecordType findSecondaryRegisterExpRecordType() {
		return (ExpRecordType) get(17000002);
	}

	@Override
	public BrokerageRecordType getWithDrawMoneyRecordType() {
		return (BrokerageRecordType) get(17000009);
	}

	@Override
	public FundsRecordType getPayFundsRecordType() {
		return (FundsRecordType) get(17000024);
	}

	@Override
	public BrokerageRecordType getPayBrokerageRecordType() {
		return (BrokerageRecordType) get(17000023);
	}


}
