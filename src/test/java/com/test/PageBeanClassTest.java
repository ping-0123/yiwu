package com.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.springmvc3.dao.CheckInsYzwDao;
import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;
import com.yinzhiwu.springmvc3.model.page.PageBean;

@Transactional
@RunWith(BlockJUnit4ClassRunner.class)
public class PageBeanClassTest {

	@Autowired private CheckInsYzwDao checkInsDao;
	
	@Test
	public void test(){
		List<LessonYzw> lessons = new ArrayList<>();
		LessonYzw lesson = new LessonYzw();
		lessons.add(lesson);
		PageBean<LessonYzw>  page = new PageBean<LessonYzw>();
		page.setData(lessons);
	}
}
