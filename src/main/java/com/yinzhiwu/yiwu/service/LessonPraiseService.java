package com.yinzhiwu.yiwu.service;

import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.yiwu.entity.LessonPraise;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;

/**
*@Author ping
*@Time  创建时间:2017年10月10日上午10:37:55
*
*/

public interface LessonPraiseService extends IBaseService<LessonPraise,Integer>{

	LessonPraise findByDistributerIdAndLessonId(Integer distributerId, Integer lessonId) throws DataNotFoundException;
	
	boolean checkIsPraised(Integer distributerId, Integer lessonId);
	
	@Transactional
	public LessonPraise doLessonPraise(LessonPraise praise);
	
	@Transactional
	public LessonPraise cancelLessonPraise(LessonPraise praise);
}
