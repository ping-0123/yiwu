package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.ExpRecordTypeDao;
import com.yinzhiwu.springmvc3.entity.ExpRecordType;
import com.yinzhiwu.springmvc3.service.ExpRecordTypeService;


@Service
public class ExpRecordTypeServiceImpl extends BaseServiceImpl<ExpRecordType, Integer> implements ExpRecordTypeService{
	
	@Autowired
	public void setExpRecordTypeDao(ExpRecordTypeDao expRecordTypeDao)
	{
		super.setBaseDao(expRecordTypeDao);
	}
}
