package com.yinzhiwu.yiwu.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.impl.BaseDaoImpl;
import com.yinzhiwu.yiwu.entity.LessonPraise;

/**
*@Author ping
*@Time  创建时间:2017年10月10日上午10:33:13
*
*/

@Repository
public class LessonPraiseDaoImpl extends BaseDaoImpl<LessonPraise, Integer> implements LessonPraiseDao {

	@Override
	public LessonPraise findByDistributerIdAndLessonId(Integer distributerId, Integer lessonId) {
		List<LessonPraise> praises = findByProperties(new String[]{"distributer.id", "lesson.id"},  new Object[]{distributerId, lessonId});
		return praises.size()>0?praises.get(0):null;
	}

}
