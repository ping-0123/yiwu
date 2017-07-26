package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinzhiwu.yiwu.dao.CheckInsYzwDao;
import com.yinzhiwu.yiwu.service.CheckInsYzwService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CheckInsYzwServiceTest {

	@Autowired
	private CheckInsYzwService service;

	@Autowired
	CheckInsYzwDao dao;

	@Test
	public void testFindCountByCustomerId() {
		int customerId = 19552;
		int i = service.findCountByCustomerId(customerId);
		System.out.println(i);
	}
}
