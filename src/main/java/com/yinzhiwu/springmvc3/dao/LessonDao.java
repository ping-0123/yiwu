package com.yinzhiwu.springmvc3.dao;

import java.util.Date;
import java.util.List;

import com.yinzhiwu.springmvc3.entity.Lesson;

public interface LessonDao {
	
	public Lesson findById(int lessonId);
	
	public List<Lesson> findLessonWeekList(
			int storeId,
			String courseType,
			String teacherName,
			String danceCatagory,
			Date startDate,
			Date endDate);
}
