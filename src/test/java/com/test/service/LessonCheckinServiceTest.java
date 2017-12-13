
package com.test.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.dao.LessonCheckInYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.LessonCheckInYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.business.SettleLessonException;
import com.yinzhiwu.yiwu.exception.data.DataConsistencyException;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;
import com.yinzhiwu.yiwu.service.LessonYzwService;
import com.yinzhiwu.yiwu.service.schedule.SettleLessonService;

/**
 * @author ping
 * @date 2017年12月11日下午4:14:46
 * @since v2.2.0
 *	
 */
public class LessonCheckinServiceTest extends BaseSpringTest {
	
	@Autowired private SettleLessonService settleLessonService;
	@Autowired private LessonYzwService lessonService;
	@Autowired private LessonCheckInYzwDao checkinDao;
	
	@Test
	@Rollback(false)
	public void testSettleOneLesson(){
		int lessonId = 244314;
		try {
			LessonYzw lesson = lessonService.get(lessonId);
			settleLessonService.settleOneLesson(lesson);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataConsistencyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SettleLessonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Rollback(false)
	public void testSettleClosedLesson(){
		try{
 			settleLessonService.settleLessons();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testFindEffictiveCheckin(){
		int lessonId = 245153;
		LessonCheckInYzw checkin = checkinDao.findEffictiveCoachCheckinByLessonId(lessonId);
		System.err.println("checkin id is " + checkin.getId());
		System.err.println( "checked in coach is " +  checkin.getTeacher().getId());
	}
	
	
	@Test
	public void testFindNeedSettledLessons(){
		List<LessonYzw> lessons = checkinDao.findNeedSettledLessons();
		System.out.println(lessons.size());
	}
}
