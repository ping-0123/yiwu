package com.yinzhiwu.yiwu.dao;

import java.util.Date;
import java.util.List;

import com.yinzhiwu.yiwu.entity.yzwOld.Lesson;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

public interface LessonDao extends IBaseDao<Lesson, Integer> {

	public Lesson findById(int lessonId) throws DataNotFoundException;

	public List<Lesson> findLessonWeekList(int storeId, String courseType, String teacherName, String danceCatagory,
			Date startDate, Date endDate) ;

	public int findOrderInCourse(Lesson l);

}
