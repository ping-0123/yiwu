package com.yinzhiwu.yiwu.dao;

import java.util.Date;
import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.enums.CourseType;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.LessonApiView;
import com.yinzhiwu.yiwu.model.view.PrivateLessonApiView;


public interface LessonYzwDao extends IBaseDao<LessonYzw, Integer> {
	List<LessonYzw> findByCourseId(String courseId);

	public LessonYzw findLastNLesson(LessonYzw thisLesson, int lastN);

	List<LessonApiView> findApiViewsByCourseId(String courseId);

	List<LessonYzw> findWeeklyLessons(int storeId, CourseType courseType, String teacherName, String danceCatagory,
			Date start, Date end);

	Integer findOrderInCourse(LessonYzw lesson);

	List<PrivateLessonApiView> findPrivateLessonApiViewsByContracNo(String contractNo);

	PageBean<LessonApiView> findPageOfClosedLessonApiViewByStoreIdAndLessonDate(Integer storeId, Date date, int pageNo,
			int pageSize);

	Long findCountByCourseId(String id);

	LessonYzw findByCourseIdAndStartDateTime(String courseId, Date start, Date end);

	LessonYzw findByCourseIdAndOrdinalNo(String courseId, Integer ordinalNo) throws DataNotFoundException;

	List<LessonYzw> findNullOrdinalLessons();

	LessonYzw findOneNullOrdinalLessons();
}
