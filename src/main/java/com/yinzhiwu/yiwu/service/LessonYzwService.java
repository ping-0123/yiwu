package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.Connotation;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.LessonApiView;

public interface LessonYzwService extends IBaseService<LessonYzw, Integer>{

	YiwuJson<List<LessonApiView>> findByCourseId(String courseId);

	LessonYzw getLastNLesson(LessonYzw thisLesson, int lastN);

	Connotation getLastNLessonConnotation(int thisLessonId, int lastN) throws Exception;

	List<LessonApiView> findApiViewByCourseId(String courseId);
	
}