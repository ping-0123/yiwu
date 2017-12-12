package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.LessonPraise;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;

/**
*@Author ping
*@Time  创建时间:2017年10月10日上午10:33:00
*
*/

public interface LessonPraiseDao extends IBaseDao<LessonPraise, Integer>{

	LessonPraise findByDistributerIdAndLessonId(Integer distributerId, Integer lessonId) throws DataNotFoundException;

	Long findCountByDistributerIdAndLessonId(Integer distributerId, Integer lessonId);

}
