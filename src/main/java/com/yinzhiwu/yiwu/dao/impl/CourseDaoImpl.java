package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.CourseDao;
import com.yinzhiwu.yiwu.entity.yzwOld.Course;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

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
