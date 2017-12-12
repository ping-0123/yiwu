package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.LessonPraiseDao;
import com.yinzhiwu.yiwu.entity.LessonPraise;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;
import com.yinzhiwu.yiwu.service.LessonPraiseService;

/**
*@Author ping
*@Time  创建时间:2017年10月10日上午10:38:41
*
*/

@Service
public class LessonPraiseServiceImpl extends BaseServiceImpl<LessonPraise,Integer> implements LessonPraiseService {

	@Autowired public void setBaseDao(LessonPraiseDao lpDao){super.setBaseDao(lpDao);}
	@Autowired private LessonPraiseDao lessonPraiseDao;
	@Autowired private ApplicationContext applicationContext;

	@Override
	public LessonPraise findByDistributerIdAndLessonId(Integer distributerId, Integer lessonId) throws DataNotFoundException {
		return lessonPraiseDao.findByDistributerIdAndLessonId(distributerId, lessonId);
	}

	@Override
	public boolean checkIsPraised(Integer distributerId, Integer lessonId) {
		return lessonPraiseDao.findCountByDistributerIdAndLessonId(distributerId, lessonId) > 0;
	}

	/**
	 * listened by {@link LessonInteractiveServiceImpl#handleLessonPraise(LessonPraise)}
	 */
	@Override
	public LessonPraise doLessonPraise(LessonPraise praise) {
		lessonPraiseDao.save(praise);
		applicationContext.publishEvent(praise);
		return praise;
	}

	@Override
	public LessonPraise cancelLessonPraise(LessonPraise praise) {
		lessonPraiseDao.update(praise);
		applicationContext.publishEvent(praise);
		return praise;
	}
	
}
