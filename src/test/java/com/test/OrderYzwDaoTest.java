package com.test;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.springmvc3.dao.impl.OrderYzwDaoImpl;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class OrderYzwDaoTest {

	
	@Autowired
	private OrderYzwDaoImpl orderYzwDaoImpl;
	
	@Test
	public void test(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH,Calendar.MARCH);
		System.out.println(calendar.getTime());
		System.out.println(orderYzwDaoImpl.test_find_produce_commission_orders(calendar.getTime()).size());
	}
}