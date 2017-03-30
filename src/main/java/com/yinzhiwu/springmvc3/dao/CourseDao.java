package com.yinzhiwu.springmvc3.dao;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.Course;

public interface CourseDao {

	 public List<Course> findByProperty(String propertyName, Object value);
	 public Course findById(String id);
}
