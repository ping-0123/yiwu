package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzwOld.Course;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

public interface CourseDao extends IBaseDao<Course, String> {

	public List<Course> findByProperty(String propertyName, Object value);

	public Course findById(String id) throws DataNotFoundException;
}
