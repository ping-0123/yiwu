package com.yinzhiwu.yiwu.service;

import java.util.Date;
import java.util.List;

import com.yinzhiwu.yiwu.entity.yzwOld.Lesson;
import com.yinzhiwu.yiwu.model.LessonList;

public interface LessonService extends IBaseService<Lesson, Integer>{
	public Lesson findById(int lessonId);
	
	public List<LessonList> findLessonWeekList(
			int storeId,
			String courseType,
			String teacherName,
			String danceCatagory,
			Date date,
			String wechat);

}
