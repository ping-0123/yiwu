package com.yinzhiwu.yiwu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.CourseYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.service.CourseYzwService;


@Service
public class CourseYzwServiceImpl extends BaseServiceImpl<CourseYzw, String> implements CourseYzwService {
	
	@Autowired private CourseYzwDao courseDao;
//	@Autowired private OrderYzwDao orderDao;
	
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
	/**
	 * {@link CourseYzwServiceImpl#setAllSumLessonTimes()}
	 */
	@Scheduled(cron="0 30 22 * * ?")
//	@Scheduled(initialDelay=10000,fixedRate=10000000)
	public void setAllSumLessonTimes(){
		List<CourseYzw> courses = courseDao.find100UnSetSumLessonTimes();
		if(logger.isInfoEnabled())
			logger.info(courses.size() + " courses is setting sum lesson times");
		
		for (CourseYzw course : courses) {
			setOne(course);
			update(course);
		}
//		setStudentCount(course);
	}
	
//	@Scheduled(initialDelay=10000, fixedRate=10)
	public void setOneSumLessonTimes(){
		try {
			CourseYzw course = courseDao.findOneByProperty("sumLessonTimes", 0);
			setOne(course);
			update(course);
		} catch (DataNotFoundException e) {
			return;
		}
	}
	
	private void setOne(CourseYzw course){
		java.util.List<LessonYzw> lessons = course.getLessons();
		course.setSumLessonTimes(lessons.size());
		
		for (LessonYzw lesson : lessons) {
			lesson.setOrdinalNo(lessons.indexOf(lesson) +1 );
		}
		
	}
	
//	private void setStudentCount(CourseYzw course){
//		course.setStudentCount(orderDao.findCountByCourseId(course.getId()));
//	}
}
