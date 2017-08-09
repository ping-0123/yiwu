package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinzhiwu.yiwu.service.LessonYzwService;

/**
*@Author ping
*@Time  创建时间:2017年8月9日下午6:29:01
*
*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class LessonYzwDaoGetTest {

	@Autowired private LessonYzwService lessonYzwService;
	
	@Test
	public void test(){
		System.err.println("LessonYzwDaoGetTest" + lessonYzwService.get(210752));
	}
}
