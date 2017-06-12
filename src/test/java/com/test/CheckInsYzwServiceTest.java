package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinzhiwu.springmvc3.dao.CheckInsYzwDao;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.service.CheckInsYzwService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CheckInsYzwServiceTest {

	@Autowired
	private CheckInsYzwService service;
	
	@Autowired CheckInsYzwDao dao;
	
	@Test
	public void testFindCountByCustomerId(){
		int customerId = 19552;
		YiwuJson<Integer> yiwuJson = service.findCountByCustomerId(customerId);
		System.out.println(yiwuJson.getData());
	}
}
