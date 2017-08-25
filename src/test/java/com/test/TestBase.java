package com.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
*@Author ping
*@Time  创建时间:2017年8月16日下午5:33:21
*
*/

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(value = "transactionManager")
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public abstract class TestBase {
//public abstract class TestBase extends AbstractTransactionalJUnit4SpringContextTests {

}
