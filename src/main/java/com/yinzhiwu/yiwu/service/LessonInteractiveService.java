package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.LessonInteractive;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

/**
*@Author ping
*@Time  创建时间:2017年10月27日上午10:02:57
*
*/

public interface LessonInteractiveService extends IBaseService<LessonInteractive,Integer> {

	LessonInteractive findByDistributerIdAndLessonId(Integer distributerId, Integer lessonId) throws DataNotFoundException;

	LessonInteractive ensureInteractive(LessonYzw lesson, Distributer distributer) throws DataNotFoundException;

}
