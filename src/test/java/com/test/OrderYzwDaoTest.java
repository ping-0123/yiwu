package com.test;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.yiwu.dao.impl.OrderYzwDaoImpl;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class OrderYzwDaoTest {

	@Autowired
	private OrderYzwDaoImpl orderYzwDaoImpl;

	@Test
	public void test() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, Calendar.MARCH);
		System.out.println(calendar.getTime());
		System.out.println(orderYzwDaoImpl.test_find_produce_commission_orders(calendar.getTime()).size());
	}

	@Test
	public void testGet() {
		String id = "20160514060-4";
		OrderYzw order = null;
		try {
			order = orderYzwDaoImpl.get(id);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(order.getId());
	}

}
