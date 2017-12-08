package com.test.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.common.entity.search.SearchOperator;
import com.yinzhiwu.yiwu.common.entity.search.SearchRequest;
import com.yinzhiwu.yiwu.common.entity.search.Searchable;
import com.yinzhiwu.yiwu.dao.LessonYzwDao;
import com.yinzhiwu.yiwu.dao.impl.LessonYzwDaoImpl;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

/**
*@Author ping
*@Time  创建时间:2017年8月16日下午9:12:44
*
*/

public class LessonYzwDaoTest extends BaseSpringTest {
	
	@Autowired private LessonYzwDao lessonYzwDao;
	@Autowired private LessonYzwDaoImpl lessonDaoImpl;
	
	@Test
	public void testFindPrivateLessonApiViewsByContractNo(){
		try {
			
			long before = System.currentTimeMillis();
			System.err.println("view count is " + lessonYzwDao.findPrivateLessonApiViewsByContracNo("360179").get(0).getStart());
			long before4824858 = System.currentTimeMillis();
			System.err.println("contract360179 executing time is " + (before4824858-before));
			System.err.println("4824858 count is " + lessonYzwDao.findPrivateLessonApiViewsByContracNo("4824858").size());
			long before4824551 = System.currentTimeMillis();
			System.err.println("contract4824858 executing time is " + (before4824551-before4824858));
			System.err.println("4824551 count is " + lessonYzwDao.findPrivateLessonApiViewsByContracNo("4824551").size());
			System.err.println("4824452 count is " + lessonYzwDao.findPrivateLessonApiViewsByContracNo("4824452").size());
			System.err.println("4622818 count is " + lessonYzwDao.findPrivateLessonApiViewsByContracNo("4622818").size());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@Test
	public void testGet(){
		Integer lessonId= 1;
		LessonYzw lesson;
		try {
			lesson = lessonYzwDao.get(lessonId);
			System.err.println("the lesson which id equal 1 is : " + lesson);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	@Rollback(false)
	public void testUpdateBlankCourseId(){
//		lessonYzwDao.updateBlankCourseId();
	}
	
	@Test
	@Rollback(false)
	public void testUpdateBlankClassRoomId(){
//		lessonYzwDao.updateBlankClassRoomId();
	}
	
	
	@Test
	public void testUpdateZeroActualTeacher(){
		lessonYzwDao.updateZeroActualTeacher();
	}
	
	@Test
	public void testFindAll(){
		Searchable<LessonYzw> search = new SearchRequest<>();
		search.and("id", SearchOperator.in, new Integer[]{1208,1209,1210});
		List<LessonYzw> content = lessonYzwDao.findAll(search).getContent();
		System.out.println("content size is " + content.size());
	}
	
	@Test
	@Rollback(false)
	public void testUpdateCoachCheckedinStatus(){
		lessonDaoImpl.updateCoachCheckedinStatus();
	}
}


