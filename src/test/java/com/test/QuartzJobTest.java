package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinzhiwu.yiwu.service.PurchaseEventService;

/**
*@Author ping
*@Time  创建时间:2017年7月15日下午2:12:53
*
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class QuartzJobTest {

	@Autowired private PurchaseEventService purchaseEventService;
	@Test
	public void test(){
		purchaseEventService.saveAllLastDayPurchaseEvents();
		System.err.println(purchaseEventService.getClass());
	}
}
