package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.TestDao;
import com.yinzhiwu.yiwu.entity.Test;
import com.yinzhiwu.yiwu.service.TestService;

@Service
public class TestServiceImpl extends BaseServiceImpl<Test, Long> implements TestService{

//	@Autowired
//	private TestDao testDao;
	

	@Autowired
	private void setBaseDao(TestDao testDao){
		super.setBaseDao(testDao);
	}
}
