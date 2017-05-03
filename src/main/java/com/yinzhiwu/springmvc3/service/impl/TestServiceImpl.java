package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.TestDao;
import com.yinzhiwu.springmvc3.entity.Test;
import com.yinzhiwu.springmvc3.service.TestService;

@Service
public class TestServiceImpl extends BaseServiceImpl<Test, Long> implements TestService{

//	@Autowired
//	private TestDao testDao;
	

	@Autowired
	private void setBaseDao(TestDao testDao){
		super.setBaseDao(testDao);
	}
}
