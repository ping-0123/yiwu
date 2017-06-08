package com.yinzhiwu.springmvc3.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.ExpRecordTypeDao;
import com.yinzhiwu.springmvc3.entity.type.ExpRecordType;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;

@Repository
public class ExpRecordTypeDaoImpl extends BaseDaoImpl<ExpRecordType, Integer> implements ExpRecordTypeDao{

	@Override
	public ExpRecordType findSubordinateRegisterExpRecordType() {
		try {
			return get(17000001);
		} catch (DataNotFoundException e) {
			return null;
		}
	}

	@Override
	public ExpRecordType findSecondaryRegisterExpRecordType() {
		try {
			return get(17000002);
		} catch (DataNotFoundException e) {
			return null;
		}
	}

	@Override
	public ExpRecordType findByShareTweetBySelf() {
		try {
			return get(17000011);
		} catch (DataNotFoundException e) {
			return null;
		}
	}

	@Override
	public ExpRecordType findByShareTweetBySubordiante() {
		try {
			return get(17000012);
		} catch (DataNotFoundException e) {
			return null;
		}
	}

}
