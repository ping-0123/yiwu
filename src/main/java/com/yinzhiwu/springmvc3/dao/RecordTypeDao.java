package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.BrokerageRecordType;
import com.yinzhiwu.springmvc3.entity.ExpRecordType;
import com.yinzhiwu.springmvc3.entity.FundsRecordType;
import com.yinzhiwu.springmvc3.entity.RecordType;

public interface RecordTypeDao extends IBaseDao<RecordType, Integer>{
	
	public FundsRecordType findRegisterFundsRecordType();
	
	public ExpRecordType findSubordinateRegisterExpRecordType();
	
	public ExpRecordType findSecondaryRegisterExpRecordType();

	public BrokerageRecordType getWithDrawMoneyRecordType();
	
	public FundsRecordType getPayFundsRecordType();
	
	public BrokerageRecordType getPayBrokerageRecordType();
	

	public BrokerageRecordType find_subordinate_first_order_commission_BrokerageRecordType();
	
	public BrokerageRecordType find_subordinate_non_first_order_commission_BrokerageRecordType();
	
	public BrokerageRecordType find_secondary_first_order_commission_BrokerageRecordType();
	
	public BrokerageRecordType find_secondary_non_first_order_commission_BrokerageRecordType();
}
