package com.yinzhiwu.yiwu.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.test.BaseSpringTest;

/**
*@Author ping
*@Time  创建时间:2017年10月27日下午3:29:46
*
*/

public class EventTest extends BaseSpringTest {
	
	@Autowired private  ApplicationContext applicationContext;
	
	@Test
	public void testPublishEvent(){
		applicationContext.publishEvent("张开涛的博客更新");
	}
}
