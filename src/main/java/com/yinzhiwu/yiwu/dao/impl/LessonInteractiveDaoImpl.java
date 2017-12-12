package com.yinzhiwu.yiwu.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.LessonInteractiveDao;
import com.yinzhiwu.yiwu.entity.LessonInteractive;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;

/**
*@Author ping
*@Time  创建时间:2017年10月27日上午10:01:23
*
*/

@Repository
public class LessonInteractiveDaoImpl extends BaseDaoImpl<LessonInteractive,Integer> implements LessonInteractiveDao{

	@Override
	public LessonInteractive findByDistributerIdAndLessonId(Integer distributerId, Integer lessonId) throws DataNotFoundException {
		return findOneByProperties(
				new String[]{"distributer.id", "lesson.id"}, 
				new Object[]{distributerId,lessonId});
	}


	
}
