package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.ExpRecordType;
import com.yinzhiwu.springmvc3.entity.FundsRecordType;
import com.yinzhiwu.springmvc3.entity.RecordType;

public interface RecordTypeDao extends IBaseDao<RecordType, Integer>{
	
	public FundsRecordType findRegisterFundsRecordType();
	
	public ExpRecordType findSubordinateRegisterExpRecordType();
	
	public ExpRecordType findSecondaryRegisterExpRecordType();
}
