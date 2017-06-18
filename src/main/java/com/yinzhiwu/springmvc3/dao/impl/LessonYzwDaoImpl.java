package com.yinzhiwu.springmvc3.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.LessonYzwDao;
import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;

@Repository
public class LessonYzwDaoImpl extends BaseDaoImpl<LessonYzw, Integer> implements LessonYzwDao {

	@Override
	public List<LessonYzw> findByCourseId(String courseId) {
		try {
			return findByProperty("course.id", courseId);
		} catch (DataNotFoundException e) {
			return new ArrayList<>();
		}
	}

}
