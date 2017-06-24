package com.yinzhiwu.springmvc3.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.LessonYzwDao;
import com.yinzhiwu.springmvc3.entity.yzw.Connotation;
import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.LessonApiView;
import com.yinzhiwu.springmvc3.service.LessonYzwService;

@Service
public class LessonYzwServiceImpl extends BaseServiceImpl<LessonYzw, Integer> implements LessonYzwService{
	
	@Autowired
	private LessonYzwDao lessonDao;
	
	@Autowired
	public void setBaseDao(LessonYzwDao lessonDao){
		super.setBaseDao(lessonDao);
	}

	@Override
	public YiwuJson<List<LessonApiView>> findByCourseId(String courseId) {
		List<LessonYzw> lessons = lessonDao.findByCourseId(courseId);
		if(lessons == null || lessons.size()==0)
			return new YiwuJson<>("no lessons found by courseId: " + courseId);
		List<LessonApiView> views = new ArrayList<>();
		for (LessonYzw l : lessons) {
			views.add(new LessonApiView(l));
		}
		return new YiwuJson<>(views);
	}
	
	
	@Override
	public List<LessonApiView> findApiViewByCourseId(String courseId){
		return lessonDao.findApiViewsByCourseId(courseId);
	}
	
	@Override
	public LessonYzw getLastNLesson(LessonYzw thisLesson, int lastN){
		return lessonDao.findLastNLesson(thisLesson, lastN);
	}
	
	@Override
	public Connotation getLastNLessonConnotation(int thisLessonId, int lastN) throws Exception{
		try {
			LessonYzw thislesson = lessonDao.get(thisLessonId);
			LessonYzw lesson =  lessonDao.findLastNLesson(thislesson, lastN);
			if(lesson != null) return lesson.getConnotation();
		} catch (DataNotFoundException e) {
			throw new Exception("未找到id为" + thisLessonId + "的课时");
		}
		return null;
	}
}
