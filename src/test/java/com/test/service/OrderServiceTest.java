package com.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.test.TestBase;
import com.yinzhiwu.yiwu.service.OrderYzwService;

/**
*@Author ping
*@Time  创建时间:2017年9月8日下午5:43:48
*
*/

public class OrderServiceTest extends TestBase{
	
	@Autowired
	private OrderYzwService orderService;
	
	@Test
	@Rollback(value=false)
	public void testSettleContract(){
		String contractNo = "YZW20170812064";
		orderService.settleContract(contractNo);
	}

}
