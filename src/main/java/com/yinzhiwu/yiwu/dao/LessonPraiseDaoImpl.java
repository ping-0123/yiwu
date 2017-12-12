package com.yinzhiwu.yiwu.dao;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.impl.BaseDaoImpl;
import com.yinzhiwu.yiwu.entity.LessonPraise;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;

/**
*@Author ping
*@Time  创建时间:2017年10月10日上午10:33:13
*
*/

@Repository
public class LessonPraiseDaoImpl extends BaseDaoImpl<LessonPraise, Integer> implements LessonPraiseDao {

	@Override
	public LessonPraise findByDistributerIdAndLessonId(Integer distributerId, Integer lessonId) throws DataNotFoundException {
		return findOneByProperties(
				new String[]{"distributer.id", "lesson.id","canceled"},  
				new Object[]{distributerId, lessonId, false});
		
		
	}

	@Override
	public Long findCountByDistributerIdAndLessonId(Integer distributerId, Integer lessonId) {
		return findCountByProperties(
				new String[]{"distributer.id", "lesson.id","canceled"},  
				new Object[]{distributerId, lessonId, false});
	}

}
