package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
*@Author ping
*@Time  创建时间:2017年8月7日下午12:05:42
*
*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ConcurrenceTest {

//	@Autowired DepositService depositService;
	
//	@Test
	public void test(){
		Thread  t1 = new DepositThread();
		Thread t2 = new DepositThread();
		
		t1.start();
		t2.start();
	}
}
