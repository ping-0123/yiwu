package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;

public interface CourseYzwDao extends IBaseDao<CourseYzw, String> {

	List<CourseYzw> find100UnSetSumLessonTimes();

}
