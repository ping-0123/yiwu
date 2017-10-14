package com.test.main;

import java.lang.reflect.InvocationTargetException;

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
import com.yinzhiwu.yiwu.util.beanutils.BeanClassUtils;
import com.yinzhiwu.yiwu.util.beanutils.ConvertException;

/**
*@Author ping
*@Time  创建时间:2017年10月13日上午9:33:31
*
*/

public class BeanClassTest extends BaseSpringTest{

	@Autowired private EmployeeYzwService empService;
	@Autowired private LessonYzwService lessonService;
	@Autowired private CourseYzwService courseService;
	
	
	@Test
	public void testConvert(){
		int empId = 22;
		EmployeeYzw emp = empService.get(empId);
		CoachVO coachVO = new CoachVO();
		EmployeeYzw emp2 = new EmployeeYzw();
		try {
			BeanClassUtils.copyProperties(emp, coachVO);
			System.err.println("coachVO is " +  new GsonBuilder().create().toJson(coachVO));
			BeanClassUtils.copyProperties(coachVO, emp2);
			System.err.println("emp2 is " + new Gson().toJson(emp2));
		} catch (IllegalArgumentException | IllegalAccessException |  SecurityException
				| InstantiationException | ConvertException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testLessonVO(){
		int lessonId = 208693;
		LessonYzw lesson = lessonService.get(lessonId);
		LessonVO lessonVO = new LessonVO();
		LessonYzw lesson2 = new LessonYzw();
		try {
			BeanClassUtils.copyProperties(lesson, lessonVO);
			System.err.println("lessonVO is " +  new GsonBuilder().create().toJson(lessonVO));
			BeanClassUtils.copyProperties(lessonVO, lesson2);
			System.err.println("lesson is " + new Gson().toJson(lesson2));
		} catch (IllegalArgumentException | IllegalAccessException |  SecurityException
				| InstantiationException | ConvertException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testCourseVO(){
		String courseId = "20170905004";
		CourseYzw course = courseService.get(courseId);
		CourseVO courseVO = new CourseVO();
		CourseYzw course2 = new CourseYzw();
		
		try {
			BeanClassUtils.copyProperties(course, courseVO);
			System.err.println("courseVO is " + new Gson().toJson(courseVO));
			System.out.println();
			BeanClassUtils.copyProperties(courseVO, course2);
			System.err.println("course2 is "  + new Gson().toJson(course2));
		} catch (IllegalArgumentException | IllegalAccessException |  SecurityException | InstantiationException
				| ConvertException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testCopyProperty(){
		CourseVO courseVO = new CourseVO();
		String uri = "aaaaaaaaaaa";
		
		BeanClassUtils.copyProperty(courseVO, "connotation.connotation", uri);
		System.err.println(courseVO.getConnotation().getConnotation());
	}
}
