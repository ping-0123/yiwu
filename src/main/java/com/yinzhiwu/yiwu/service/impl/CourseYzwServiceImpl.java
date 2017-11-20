package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.CourseYzwDao;
import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.service.CourseYzwService;


@Service
public class CourseYzwServiceImpl extends BaseServiceImpl<CourseYzw, String> implements CourseYzwService {
	
	@Autowired private CourseYzwDao courseDao;
	@Autowired private OrderYzwDao orderDao;
	
	@Autowired
	public void setBaseDao(CourseYzwDao courseYzwDao) {
		super.setBaseDao(courseYzwDao);
	}
	
//	@Scheduled(initialDelay=10000,fixedRate=931536000)
//	public void setAll(){
//		List<CourseYzw> unsettedCourses = courseDao.findByProperty("sumLessonTimes", null);
//		for (CourseYzw course : unsettedCourses) {
//			setOne(course);
//		}
//	}
//	
	
//	@Scheduled(initialDelay=10000, fixedRate=10)
	public void setOne(){
		CourseYzw course;
		try {
			course = courseDao.findOneByProperty("sumLessonTimes",null);
		} catch (DataNotFoundException e) {
			logger.error(e.getMessage());
			return;
		}
		
		setSumLessonTimesAndLessonOrdialNo(course);
		setStudentCount(course);
		update(course);
	}
	
	private void setSumLessonTimesAndLessonOrdialNo(CourseYzw course){
		java.util.List<LessonYzw> lessons = course.getLessons();
		course.setSumLessonTimes(lessons.size());
		
		for (LessonYzw lesson : lessons) {
			lesson.setOrdinalNo(lessons.indexOf(lesson) +1 );
		}
		
	}
	
	private void setStudentCount(CourseYzw course){
		course.setStudentCount(orderDao.findCountByCourseId(course.getId()));
	}
}
