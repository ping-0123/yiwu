package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

public interface CourseYzwDao extends IBaseDao<CourseYzw, String> {

	CourseYzw findOneUnSetSumLessonTimes() throws DataNotFoundException;

}
