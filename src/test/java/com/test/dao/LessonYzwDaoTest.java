package com.test.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.dao.LessonYzwDao;

/**
*@Author ping
*@Time  创建时间:2017年8月16日下午9:12:44
*
*/

public class LessonYzwDaoTest extends BaseSpringTest {
	
	@Autowired private LessonYzwDao lessonYzwDao;
	
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
}
