package com.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.dao.impl.DistributerDaoImpl;

public class DistributerDaoTest extends BaseSpringTest{
	
	@Autowired DistributerDaoImpl distributerDaoImpl;
	
	@Test
//	@Rollback(false)
	public void testUdateBingable(){
		distributerDaoImpl.setUnBindChangable();
	}
}
