package com.test.service;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.TestBase;
import com.yinzhiwu.yiwu.dao.LessonYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.service.AppointmentEventService;
import com.yinzhiwu.yiwu.util.CalendarUtil;

/**
*@Author ping
*@Time  创建时间:2017年8月23日下午9:11:41
*
*/

public class AppointmentEventServiceTest extends TestBase {

	@Autowired private AppointmentEventService appointmentEventService;
	@Autowired private LessonYzwDao lessonDao;
	
	@Test
	public void testAppoint(){
		int distributerId = 3000142;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 15);
		List<LessonYzw> lessons = lessonDao.findByProperties(
				new String[]{"store.id", "lessonDate", "courseType"}, 
				new Object[]{63, CalendarUtil.getDayBegin(calendar).getTime(), CourseType.OPENED});
		System.err.println("lesson count is " + lessons.size());
		for (LessonYzw lesson : lessons) {
			try {
				appointmentEventService.saveAppoint(distributerId, lesson.getId());
				System.out.println("预约成功");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	@Test
	public void test(){
		System.out.println("hello world");
	}
}
