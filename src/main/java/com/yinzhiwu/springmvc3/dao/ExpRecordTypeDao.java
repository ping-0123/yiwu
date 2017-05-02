package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.ExpRecordType;

public interface ExpRecordTypeDao extends IBaseDao<ExpRecordType, Integer> {
	
	public ExpRecordType findSubordinateRegisterExpRecordType();
	
	public ExpRecordType findSecondaryRegisterExpRecordType();
}
