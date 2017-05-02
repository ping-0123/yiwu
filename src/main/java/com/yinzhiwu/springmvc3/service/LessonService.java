package com.yinzhiwu.springmvc3.service;

import java.util.Date;
import java.util.List;

import com.yinzhiwu.springmvc3.entity.Customer;
import com.yinzhiwu.springmvc3.entity.Lesson;
import com.yinzhiwu.springmvc3.model.LessonList;

public interface LessonService {
	public Lesson findById(int lessonId);
	
	public List<LessonList> findLessonWeekList(
			int storeId,
			String courseType,
			String teacherName,
			String danceCatagory,
			Date date,
			Customer c);

	public void save(Lesson lesson);
}
