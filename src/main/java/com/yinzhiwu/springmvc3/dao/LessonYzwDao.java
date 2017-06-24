package com.yinzhiwu.springmvc3.dao;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;
import com.yinzhiwu.springmvc3.model.view.LessonApiView;

public interface LessonYzwDao extends IBaseDao<LessonYzw, Integer> {

	List<LessonYzw> findByCourseId(String courseId);
	
	public LessonYzw findLastNLesson(LessonYzw thisLesson, int lastN);

	List<LessonApiView> findApiViewsByCourseId(String courseId);
}
