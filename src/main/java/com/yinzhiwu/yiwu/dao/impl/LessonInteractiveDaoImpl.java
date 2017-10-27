package com.yinzhiwu.yiwu.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.LessonInteractiveDao;
import com.yinzhiwu.yiwu.entity.LessonInteractive;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

/**
*@Author ping
*@Time  创建时间:2017年10月27日上午10:01:23
*
*/

@Repository
public class LessonInteractiveDaoImpl extends BaseDaoImpl<LessonInteractive,Integer> implements LessonInteractiveDao{

	@Override
	public LessonInteractive findByCommenterIdAndLessonId(Integer commenterId, Integer lessonId) throws DataNotFoundException {
		LessonInteractive interactive = findOneByProperties(
				new String[]{"commenter.id", "lesson.id"}, 
				new Object[]{commenterId,lessonId});
		if(interactive == null ) throw new DataNotFoundException(LessonInteractive.class,"commenter.id", commenterId);
		return interactive;
	}

	
}
