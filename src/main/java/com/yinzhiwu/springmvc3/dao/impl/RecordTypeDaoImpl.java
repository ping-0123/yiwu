package com.yinzhiwu.springmvc3.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.RecordTypeDao;
import com.yinzhiwu.springmvc3.entity.BrokerageRecordType;
import com.yinzhiwu.springmvc3.entity.ExpRecordType;
import com.yinzhiwu.springmvc3.entity.FundsRecordType;
import com.yinzhiwu.springmvc3.entity.RecordType;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;

@Repository
public class RecordTypeDaoImpl extends BaseDaoImpl<RecordType,Integer> implements RecordTypeDao{

	@Override
	public FundsRecordType findRegisterFundsRecordType() {
		try {
			return (FundsRecordType) get(17000004);
		} catch (DataNotFoundException e) {
			return null;
		}
	}

	@Override
	public ExpRecordType findSubordinateRegisterExpRecordType() {
		try {
			return (ExpRecordType) get(17000001);
		} catch (DataNotFoundException e) {
			return null;
		}
	}

	@Override
	public ExpRecordType findSecondaryRegisterExpRecordType() {
		try {
			return (ExpRecordType) get(17000002);
		} catch (DataNotFoundException e) {
			return null;
		}
	}

	@Override
	public BrokerageRecordType getWithDrawMoneyRecordType() {
		try {
			return (BrokerageRecordType) get(17000009);
		} catch (DataNotFoundException e) {
			return null;
		}
	}

	@Override
	public FundsRecordType getPayFundsRecordType() {
		try {
			return (FundsRecordType) get(17000024);
		} catch (DataNotFoundException e) {
			return null;
		}
	}

	@Override
	public BrokerageRecordType getPayBrokerageRecordType() {
		try {
			return (BrokerageRecordType) get(17000023);
		} catch (DataNotFoundException e) {
			return null;
		}
	}

	@Override
	public BrokerageRecordType find_subordinate_first_order_commission_BrokerageRecordType() {
		try {
			return (BrokerageRecordType) get(17000005);
		} catch (DataNotFoundException e) {
			return null;
		}
	}

	@Override
	public BrokerageRecordType find_subordinate_non_first_order_commission_BrokerageRecordType() {
		try {
			return (BrokerageRecordType) get(17000007);
		} catch (DataNotFoundException e) {
			return null;
		}
	}

	@Override
	public BrokerageRecordType find_secondary_first_order_commission_BrokerageRecordType() {
		try {
			return (BrokerageRecordType) get(17000006);
		} catch (DataNotFoundException e) {
			return null;
		}
	}

	@Override
	public BrokerageRecordType find_secondary_non_first_order_commission_BrokerageRecordType() {
		try {
			return (BrokerageRecordType) get(17000008);
		} catch (DataNotFoundException e) {
			return null;
		}
	}


}
