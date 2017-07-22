package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmbededPoTest {

	
	@Autowired
	private OrderYzwDao orderYzwDao;
	
	@Test
	public void testEmbeded(){
		OrderYzw order = null;
		try {
			order = orderYzwDao.get("20170423035");
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Contract contract = order.getContract();
		System.out.println(contract.getValidityTimes());
//		System.out.println(contract.getCustomer().getName());
//		System.out.println(order.getCustomer().getName());
	}
	
}
