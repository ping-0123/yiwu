package com.yinzhiwu.springmvc3.service;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.LessonApiView;

public interface LessonYzwService extends IBaseService<LessonYzw, Integer>{

	YiwuJson<List<LessonApiView>> findByCourseId(String courseId);
	
}
