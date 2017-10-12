package com.test.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.dao.CustomerYzwDao;

/**
*@Author ping
*@Time  创建时间:2017年8月16日下午7:56:01
*
*/

public class CustomerYzwDaoTest extends BaseSpringTest {

	@Autowired private CustomerYzwDao customerDao;
	
	@Test
	public void testMatchCustomer(){
		System.err.println("matched customer is " + customerDao.findByPhoneNo("13396557928").getMemberCard());
		System.err.println("matched customer is " + customerDao.findByPhoneNo("13396557928").getMemberCard());
		System.err.println("matched customer is " + customerDao.findByPhoneNo("13396557928").getMemberCard());
		System.err.println("matched customer is " + customerDao.findByPhoneNo("13396557928").getMemberCard());
		System.err.println("matched customer is " + customerDao.findByPhoneNo("13396557928").getMemberCard());
		System.err.println("matched customer is " + customerDao.findByPhoneNo("13396557928").getMemberCard());
		System.err.println("matched customer is " + customerDao.findByPhoneNo("13396557928").getMemberCard());
		System.err.println("matched customer is " + customerDao.findByPhoneNo("13396557928").getMemberCard());
		System.err.println("matched customer is " + customerDao.findByPhoneNo("13396557928").getMemberCard());
	}
}
