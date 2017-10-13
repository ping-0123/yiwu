package com.test.main;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.model.view.CoachVO;
import com.yinzhiwu.yiwu.model.view.LessonVO;
import com.yinzhiwu.yiwu.service.EmployeeYzwService;
import com.yinzhiwu.yiwu.service.LessonYzwService;
import com.yinzhiwu.yiwu.util.convert.BeanUtils;
import com.yinzhiwu.yiwu.util.convert.ConvertException;

/**
*@Author ping
*@Time  创建时间:2017年10月13日上午9:33:31
*
*/

public class BeanClassTest extends BaseSpringTest{

	@Autowired private EmployeeYzwService empService;
	@Autowired private LessonYzwService lessonService;
	
	
	@Test
	public void testConvert(){
		int empId = 22;
		EmployeeYzw emp = empService.get(empId);
		CoachVO coachVO = new CoachVO();
		EmployeeYzw emp2 = new EmployeeYzw();
		try {
			BeanUtils.copyProperties(emp, coachVO);
			System.err.println("coachVO is " +  new GsonBuilder().create().toJson(coachVO));
			BeanUtils.copyProperties(coachVO, emp2);
			System.err.println("emp2 is " + new Gson().toJson(emp2));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException
				| InstantiationException | ConvertException | NoSuchMethodException | InvocationTargetException e) {
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
			BeanUtils.copyProperties(lesson, lessonVO);
			System.err.println("lessonVO is " +  new GsonBuilder().create().toJson(lessonVO));
			BeanUtils.copyProperties(lessonVO, lesson2);
			System.err.println("lesson is " + new Gson().toJson(lesson2));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException
				| InstantiationException | ConvertException | NoSuchMethodException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
