package com.yinzhiwu.springmvc3.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.CourseDao;
import com.yinzhiwu.springmvc3.entity.Course;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;

@Repository
public class CourseDaoImpl extends BaseDaoImpl<Course, String> implements CourseDao{

	@Override
	public List<Course> findByProperty(String propertyName, Object value) {
		return null;
	}

	@Override
	public Course findById(String id) throws DataNotFoundException {
		return get(id);
	}

}
