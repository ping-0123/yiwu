package com.yinzhiwu.springmvc3.dao;

import java.util.Date;
import java.util.List;

import com.yinzhiwu.springmvc3.entity.yzwOld.Lesson;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;

public interface LessonDao extends IBaseDao<Lesson, Integer> {
	
	public Lesson findById(int lessonId) throws DataNotFoundException;
	
	public List<Lesson> findLessonWeekList(
			int storeId,
			String courseType,
			String teacherName,
			String danceCatagory,
			Date startDate,
			Date endDate) throws DataNotFoundException;

	public int findOrderInCourse(Lesson l);
}
