package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.springmvc3.dao.OrderYzwDao;
import com.yinzhiwu.springmvc3.entity.yzw.Contract;
import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmbededPoTest {

	
	@Autowired
	private OrderYzwDao orderYzwDao;
	
	@Test
	public void testEmbeded(){
		OrderYzw order = orderYzwDao.get("20170423035");
		Contract contract = order.getContract();
		System.out.println(contract.getValidityTimes());
//		System.out.println(contract.getCustomer().getName());
//		System.out.println(order.getCustomer().getName());
	}
	
}
