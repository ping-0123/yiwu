package com.yinzhiwu.springmvc3.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.ExpRecordTypeDao;
import com.yinzhiwu.springmvc3.entity.ExpRecordType;

@Repository
public class ExpRecordTypeDaoImpl extends BaseDaoImpl<ExpRecordType, Integer> implements ExpRecordTypeDao{

	@Override
	public ExpRecordType findSubordinateRegisterExpRecordType() {
		return get(17000001);
	}

	@Override
	public ExpRecordType findSecondaryRegisterExpRecordType() {
		return get(17000002);
	}

}
