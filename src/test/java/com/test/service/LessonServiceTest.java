package com.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.service.impl.LessonYzwServiceImpl;

/**
*@Author ping
*@Time  创建时间:2017年11月8日下午4:31:26
*
*/

public class LessonServiceTest extends BaseSpringTest {
	
	@Autowired LessonYzwServiceImpl lessonService;
	
	@Test
	public void testSetOrdinal(){
//		lessonService.changeLessonOrdinalNo();
	}
}
