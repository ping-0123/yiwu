package com.yinzhiwu.yiwu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.LessonYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.Connotation;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.LessonApiView;
import com.yinzhiwu.yiwu.service.LessonYzwService;

@Service
public class LessonYzwServiceImpl extends BaseServiceImpl<LessonYzw, Integer> implements LessonYzwService {

	@Autowired
	private LessonYzwDao lessonDao;

	@Autowired
	public void setBaseDao(LessonYzwDao lessonDao) {
		super.setBaseDao(lessonDao);
	}

	@Override
	public YiwuJson<List<LessonApiView>> findByCourseId(String courseId) {
		List<LessonYzw> lessons = lessonDao.findByCourseId(courseId);
		if (lessons == null || lessons.size() == 0)
			return new YiwuJson<>("no lessons found by courseId: " + courseId);
		List<LessonApiView> views = new ArrayList<>();
		for (LessonYzw l : lessons) {
			views.add(new LessonApiView(l));
		}
		return new YiwuJson<>(views);
	}

	@Override
	public List<LessonApiView> findApiViewByCourseId(String courseId) {
		return lessonDao.findApiViewsByCourseId(courseId);
	}

	@Override
	public LessonYzw getLastNLesson(LessonYzw thisLesson, int lastN) {
		return lessonDao.findLastNLesson(thisLesson, lastN);
	}

	@Override
	public Connotation getLastNLessonConnotation(int thisLessonId, int lastN) throws Exception {
		LessonYzw thislesson = lessonDao.get(thisLessonId);
		LessonYzw lesson = lessonDao.findLastNLesson(thislesson, lastN);
		if (lesson != null)
			return lesson.getConnotation();
		return null;
	}
}
