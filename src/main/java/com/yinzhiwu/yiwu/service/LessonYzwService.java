package com.yinzhiwu.yiwu.service;

import java.util.Date;
import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.LessonConnotation;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.enums.CourseType;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.LessonApiView;
import com.yinzhiwu.yiwu.model.view.PrivateLessonApiView;

public interface LessonYzwService extends IBaseService<LessonYzw, Integer> {

	YiwuJson<List<LessonApiView>> findByCourseId(String courseId);

	LessonYzw getLastNLesson(LessonYzw thisLesson, int lastN);

	LessonConnotation getLastNLessonConnotation(int thisLessonId, int lastN) throws Exception;

	List<LessonApiView> findApiViewByCourseId(String courseId);

	Object findWeeklyLessons(int storeId, CourseType courseType, String teacherName, String danceCatagory, Date date,
			String weChat);

	YiwuJson<List<PrivateLessonApiView>> findPrivateLessonApiViewsByContracNo(String contractNo);

	YiwuJson<PageBean<LessonApiView>> findPageOfClosedLessonApiViewByStoreIdAndLessonDate(Integer storeId, Date date,
			int pageNo, int pageSize);

	Object findLessonWeekList(int storeId, String courseType, String teacherName, String danceCatagory, Date date,
			String weChat);

	LessonYzw findComingLessonByCourseId(String courseId);
	public  void setConnatationUrls(LessonConnotation con);

	LessonYzw findByCourseIdAndOrdinalNo(String id, Integer ordinalNo) throws DataNotFoundException;

	List<LessonYzw> findOpenedLessonsOfYesterday();

}
