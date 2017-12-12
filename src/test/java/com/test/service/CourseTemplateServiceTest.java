package com.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.entity.CourseTemplate;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;
import com.yinzhiwu.yiwu.service.CourseTemplateService;
import com.yinzhiwu.yiwu.service.CourseYzwService;

/**
*@Author ping
*@Time  创建时间:2017年11月23日下午2:15:28
*
*/

public class CourseTemplateServiceTest extends BaseSpringTest{
	
	@Autowired private CourseYzwService courseService;
	@Autowired private CourseTemplateService courseTemplateService;
	
	
	@Test
	public void testFindMapedCourseTemplate(){
		String courseId = "20171007002";
		try {
			CourseYzw course = courseService.get(courseId);
			CourseTemplate ct = courseTemplateService.findMapedCourseTemplate(course);
			if(null != ct)
				System.err.println("course template is " + ct.getName());
			else {
				System.err.println("course template is  not found ");
			}
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	@Test
	@Rollback(false)
	public void testSetOneConnotation(){
		String courseId = "20171007002";
		try {
			CourseYzw course = courseService.get(courseId);
			courseService.setOneConnotation(course);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
