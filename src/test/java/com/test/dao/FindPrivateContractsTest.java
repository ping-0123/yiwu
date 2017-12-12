package com.test.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;

/**
*@Author ping
*@Time  创建时间:2017年8月16日下午5:49:46
*
*/

public class FindPrivateContractsTest  extends BaseSpringTest{
	
	@Autowired private OrderYzwDao orderDao;
	
	
	@Test
	public void testGet(){
		OrderYzw order;
		try {
			order = orderDao.get("20170822048");
			System.err.println("hash code is ...." + order.hashCode());
			System.err.println("the customer name of order is .." + order.getCustomer().getName());
			System.err.println("the contract of order is .." + order.getContract());
			System.err.println("the coruse of the order is .. " + order.getContract().getCourse());
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testIsFirstOrder(){
		OrderYzw order;
		try {
			order = orderDao.get("20170822048");
			System.err.println(orderDao.isCustomerFirstOrder(order));
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
