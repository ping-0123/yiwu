package com.yinzhiwu.yiwu.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.event.ContentEvent;

/**
*@Author ping
*@Time  创建时间:2017年10月27日下午3:29:46
*
*/

public class EventTest extends BaseSpringTest {
	
	@Autowired private  ApplicationContext applicationContext;
	
	@Test
	@Rollback(false)
	public void testPublishEvent(){
		System.out.println("event publisher execute in thread " + Thread.currentThread());
		applicationContext.publishEvent(new ContentEvent("张开涛的博客更新"));
	}
}
