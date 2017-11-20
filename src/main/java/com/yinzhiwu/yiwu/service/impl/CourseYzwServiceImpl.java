package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Scheduled(cron="0/1 * * * * ?")
	@Transactional
	public void scheduledSetOne_StudentCount_SumLessonTimesAndLessonOrdialNo(){
		CourseYzw course;
		try {
			course = courseDao.findOneByProperty("sumLessonTimes", null);
		} catch (DataNotFoundException e) {
			return;
		}
		setSumLessonTimesAndLessonOrdialNo(course);
		setStudentCount(course);
		update(course);
	}
	
//	@Transactional
	private void setSumLessonTimesAndLessonOrdialNo(CourseYzw course){
		java.util.List<LessonYzw> lessons = course.getLessons();
		course.setSumLessonTimes(lessons.size());
		
		for (LessonYzw lesson : lessons) {
			lesson.setOrdinalNo(lessons.indexOf(lesson) +1 );
		}
		
	}
	
//	@Transactional
	private void setStudentCount(CourseYzw course){
		course.setStudentCount(orderDao.findCountByCourseId(course.getId()));
	}
}
