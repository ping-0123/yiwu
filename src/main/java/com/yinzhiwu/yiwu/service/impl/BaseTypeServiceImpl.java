package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.BaseTypeDao;
import com.yinzhiwu.yiwu.entity.type.BaseType;
import com.yinzhiwu.yiwu.service.BaseTypeService;

@Service
public class BaseTypeServiceImpl extends BaseServiceImpl<BaseType, Integer> implements BaseTypeService {

	@Autowired
	public void setBaseTypeDao(@Qualifier("baseTypeDaoImpl") BaseTypeDao baseTypeDao) {
		super.setBaseDao(baseTypeDao);
	}
}
