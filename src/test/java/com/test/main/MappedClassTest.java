package com.test.main;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.model.view.CoachVO;
import com.yinzhiwu.yiwu.model.view.CourseVO;
import com.yinzhiwu.yiwu.model.view.LessonVO;
import com.yinzhiwu.yiwu.service.CourseYzwService;
import com.yinzhiwu.yiwu.service.EmployeeYzwService;
import com.yinzhiwu.yiwu.service.LessonYzwService;
import com.yinzhiwu.yiwu.util.beanutils.MapedClassUtils;
import com.yinzhiwu.yiwu.util.beanutils.MapedPropertyDismatchException;
import com.yinzhiwu.yiwu.util.beanutils.MapedClassDismatchException;

/**
*@Author ping
*@Time  创建时间:2017年10月13日上午9:33:31
*
*/

public class MappedClassTest extends BaseSpringTest{

	@Autowired private EmployeeYzwService empService;
	@Autowired private LessonYzwService lessonService;
	@Autowired private CourseYzwService courseService;
	
	
//	@Test
	public void testConvert(){
		int empId = 22;
		EmployeeYzw emp = empService.get(empId);
		CoachVO coachVO = new CoachVO();
		EmployeeYzw emp2 = new EmployeeYzw();
		try {
			MapedClassUtils.copyProperties(emp, coachVO);
			System.err.println("coachVO is " +  new GsonBuilder().create().toJson(coachVO));
			MapedClassUtils.copyProperties(coachVO, emp2);
			System.err.println("emp2 is " + new Gson().toJson(emp2));
		} catch (IllegalArgumentException |   SecurityException  | MapedClassDismatchException | MapedPropertyDismatchException
				e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	@Test
	public void testLessonVO(){
		int lessonId = 208693;
		LessonYzw lesson = lessonService.get(lessonId);
		LessonVO lessonVO = new LessonVO();
		LessonYzw lesson2 = new LessonYzw();
		try {
			MapedClassUtils.copyProperties(lesson, lessonVO);
			System.err.println("lessonVO is " +  new GsonBuilder().create().toJson(lessonVO));
			MapedClassUtils.copyProperties(lessonVO, lesson2);
			System.err.println("lesson is " + new Gson().toJson(lesson2));
		} catch (IllegalArgumentException |  SecurityException
				 | MapedClassDismatchException |  MapedPropertyDismatchException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
//	@Test
	public void testCourseVO(){
		String courseId = "20170905004";
		CourseYzw course = courseService.get(courseId);
		CourseVO courseVO = new CourseVO();
		CourseYzw course2 = new CourseYzw();
		
		try {
			MapedClassUtils.copyProperties(course, courseVO);
			System.err.println("courseVO is " + new Gson().toJson(courseVO));
			System.out.println();
			MapedClassUtils.copyProperties(courseVO, course2);
			System.err.println("course2 is "  + new Gson().toJson(course2));
		} catch (IllegalArgumentException |  SecurityException
				| MapedClassDismatchException | MapedPropertyDismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test(){
		System.out.println("fuck you!");
	}
}
