package com.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.yiwu.dao.LessonYzwDao;
import com.yinzhiwu.yiwu.model.view.LessonApiView;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class FindApiViewTest {
	
	@Autowired LessonYzwDao lessonDao;
	
	@Test
	public void test(){
		List<LessonApiView> views = lessonDao.findApiViewsByCourseId("20170502002");
		System.out.println(views.size());
		for (LessonApiView v : views) {
			System.out.println(v.getDueTeacher());
			System.out.println(v.getDanceName());
			System.out.println(v.getDate());
			System.out.println(v.getStart());
			System.out.println();
		}
	}

}
