package com.test.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.TestBase;
import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.model.view.PrivateContractApiView;

/**
*@Author ping
*@Time  创建时间:2017年8月16日下午5:49:46
*
*/

public class FindPrivateContractsTest  extends TestBase{
	
	@Autowired private OrderYzwDao orderDao;
	
	@Test
	public void testFindPrivateContracts(){
		 List<PrivateContractApiView> views = orderDao.getPrivateContractsByCustomer(19308);
		System.err.println(views.size());
	}
	
	@Test
	public void testGet(){
		OrderYzw order = orderDao.get("20170822048");
		if(order == null )
			return;
		System.err.println("hash code is ...." + order.hashCode());
		System.err.println("the customer name of order is .." + order.getCustomer().getName());
		System.err.println("the contract of order is .." + order.getContract());
		System.err.println("the coruse of the order is .. " + order.getContract().getCourse());
	}
	
	@Test
	public void testIsFirstOrder(){
		OrderYzw order = orderDao.get("20170822048");
		System.err.println(orderDao.isCustomerFirstOrder(order));
	}
}
