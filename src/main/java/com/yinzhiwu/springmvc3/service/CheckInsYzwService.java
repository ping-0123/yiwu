package com.yinzhiwu.springmvc3.service;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.yzw.CheckInsYzw;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.LessonApiView;

public interface CheckInsYzwService extends IBaseService<CheckInsYzw, Integer> {


	YiwuJson<List<LessonApiView>> findByCustomerId(int customerId);

	int findCountByCustomerId(int customerId);

	void saveByCutomerByLesson(int customerId, int lessonId) throws DataNotFoundException, Exception;

}
