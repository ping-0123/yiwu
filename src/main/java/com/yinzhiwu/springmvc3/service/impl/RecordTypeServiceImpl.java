package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.RecordTypeDao;
import com.yinzhiwu.springmvc3.entity.type.RecordType;
import com.yinzhiwu.springmvc3.service.RecordTypeService;

@Repository
public class RecordTypeServiceImpl extends BaseServiceImpl<RecordType, Integer> implements RecordTypeService{

	@Autowired
	public void setRecordTypeDaoDao(RecordTypeDao recordTypeDao){
		super.setBaseDao(recordTypeDao);
	}
}
