package com.test.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.TestBase;
import com.yinzhiwu.yiwu.dao.OrderYzwDao;
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
}
