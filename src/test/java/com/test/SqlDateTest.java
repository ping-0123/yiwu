package com.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinzhiwu.yiwu.entity.yzwOld.Lesson;
import com.yinzhiwu.yiwu.service.LessonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SqlDateTest {
	private static final Log logger = LogFactory.getLog(SqlDateTest.class);
	
	@Autowired
	@Qualifier(value="lessonServiceImplTwo")
	private LessonService lessonService;
	
	@Test
	public void test(){
		Lesson lesson = lessonService.get(202400);
		logger.info(lesson.getLessonDate());
	}
}
