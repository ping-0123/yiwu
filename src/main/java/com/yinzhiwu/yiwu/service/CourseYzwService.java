package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;

public interface CourseYzwService extends IBaseService<CourseYzw, String> {

	public void setOneConnotation(CourseYzw course);

	/**
	 * @param courseId
	 */
	public void updateCourseStatusToUnChecked(String id);
	
	public void updateCourseStatusToUnChecked(CourseYzw course);

}
