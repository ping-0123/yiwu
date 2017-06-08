package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.type.ExpRecordType;

public interface ExpRecordTypeDao extends IBaseDao<ExpRecordType, Integer> {
	
	public ExpRecordType findSubordinateRegisterExpRecordType();
	
	public ExpRecordType findSecondaryRegisterExpRecordType();

	public ExpRecordType findByShareTweetBySelf();

	public ExpRecordType findByShareTweetBySubordiante();
}
