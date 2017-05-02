package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.ExpRecord;
import com.yinzhiwu.springmvc3.entity.RecordType;

public interface ExpRecordDao extends IBaseDao<ExpRecord, Integer>{
	
	public RecordType findRegisterRecordType();

	public RecordType findSecondaryRegisterRecordType();
}
