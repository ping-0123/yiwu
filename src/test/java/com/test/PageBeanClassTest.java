package com.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.springmvc3.dao.CheckInsYzwDao;
import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;
import com.yinzhiwu.springmvc3.model.page.PageBean;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PageBeanClassTest {

	@Autowired private CheckInsYzwDao checkInsDao;
	@Autowired private DistributerDao distributerDao;
	
	@Test
	public void test(){
		List<LessonYzw> lessons = new ArrayList<>();
		LessonYzw lesson = new LessonYzw();
		lessons.add(lesson);
		PageBean<LessonYzw>  page = new PageBean<LessonYzw>();
		page.setData(lessons);
	}
	
	@Test
	public void testFindPageByProperties(){
		PageBean<Distributer> page = distributerDao.findPageByProperty("customer.name", "孟，小主持人", 1, 10);
		System.err.println(page.getList().size());
		System.err.println(page.getList().get(0).getAccount());
	}
}
