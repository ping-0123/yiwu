package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.LessonInteractive;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;

/**
*@Author ping
*@Time  创建时间:2017年10月27日上午9:59:17
*
*/

public interface LessonInteractiveDao extends IBaseDao<LessonInteractive,Integer>{

	LessonInteractive findByDistributerIdAndLessonId(Integer distributerId, Integer lessonId) throws DataNotFoundException;

}
