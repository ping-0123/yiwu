package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.service.OrderYzwService;

/**
*@Author ping
*@Time  创建时间:2017年7月20日下午2:06:08
*
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ModifyTest {

	@Autowired OrderYzwService orderService;
	
	@Test
	public void testModify(){
		OrderYzw  order = new OrderYzw();
		order.seteContractStatus(true);
		try {
			orderService.modify("38244", order);
		} catch (IllegalArgumentException | IllegalAccessException | DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
