
package com.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.DataConsistencyException;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.service.LessonYzwService;
import com.yinzhiwu.yiwu.service.impl.LessonCheckinServiceImpl;

/**
 * @author ping
 * @date 2017年12月11日下午4:14:46
 * @since v2.2.0
 *	
 */
public class LessonCheckinServiceTest extends BaseSpringTest {
	
	@Autowired private LessonCheckinServiceImpl checkinService;
	@Autowired private LessonYzwService lessonService;
	
	
	@Test
	@Rollback(false)
	public void testSettleOneLesson(){
		int lessonId = 213827;
		try {
			LessonYzw lesson = lessonService.get(lessonId);
			checkinService.settleOneLesson(lesson);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataConsistencyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Rollback(false)
	public void testSettleClosedLesson(){
		int lessonId = 212858;

	}
}
