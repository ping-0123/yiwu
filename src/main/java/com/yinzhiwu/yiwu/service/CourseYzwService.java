package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;

public interface CourseYzwService extends IBaseService<CourseYzw, String> {

	public void setOneConnotation(CourseYzw course);

	/**
	 * @param courseId
	 */
	public void updateCourseStatus(String id);
	
	public void updateCourseStatus(CourseYzw course);

}
