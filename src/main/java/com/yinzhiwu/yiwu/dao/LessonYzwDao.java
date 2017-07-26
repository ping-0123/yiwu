package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.model.view.LessonApiView;

public interface LessonYzwDao extends IBaseDao<LessonYzw, Integer> {

	List<LessonYzw> findByCourseId(String courseId);

	public LessonYzw findLastNLesson(LessonYzw thisLesson, int lastN);

	List<LessonApiView> findApiViewsByCourseId(String courseId);
}
