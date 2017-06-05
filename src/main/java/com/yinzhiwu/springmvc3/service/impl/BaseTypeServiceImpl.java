package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.BaseTypeDao;
import com.yinzhiwu.springmvc3.entity.BaseType;
import com.yinzhiwu.springmvc3.service.BaseTypeService;

@Service
public class BaseTypeServiceImpl extends BaseServiceImpl<BaseType, Integer> implements BaseTypeService{

	
	@Autowired
	public void setBaseTypeDao(@Qualifier("baseTypeDaoImpl") BaseTypeDao baseTypeDao)
	{
		super.setBaseDao(baseTypeDao);
	}
}
