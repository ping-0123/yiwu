package com.yinzhiwu.springmvc3.dao;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.yzwOld.Course;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;

public interface CourseDao extends IBaseDao<Course, String> {

	 public List<Course> findByProperty(String propertyName, Object value);
	 public Course findById(String id) throws DataNotFoundException;
}
