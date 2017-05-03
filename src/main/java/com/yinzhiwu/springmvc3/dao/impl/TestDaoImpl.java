package com.yinzhiwu.springmvc3.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.TestDao;
import com.yinzhiwu.springmvc3.entity.Test;

@Repository
public class TestDaoImpl extends BaseDaoImpl<Test, Long> implements TestDao{
	
}
