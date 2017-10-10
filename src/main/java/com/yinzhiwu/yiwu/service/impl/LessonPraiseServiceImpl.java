package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.LessonPraiseDao;
import com.yinzhiwu.yiwu.entity.LessonPraise;
import com.yinzhiwu.yiwu.service.LessonPraiseService;

/**
*@Author ping
*@Time  创建时间:2017年10月10日上午10:38:41
*
*/

@Service
public class LessonPraiseServiceImpl extends BaseServiceImpl<LessonPraise,Integer> implements LessonPraiseService {

	@Autowired public void setBaseDao(LessonPraiseDao lpDao){super.setBaseDao(lpDao);}
	@Autowired private LessonPraiseDao lpDao;

	@Override
	public LessonPraise findByDistributerIdAndLessonId(Integer distributerId, Integer lessonId) {
		return lpDao.findByDistributerIdAndLessonId(distributerId, lessonId);
	}
	
}
