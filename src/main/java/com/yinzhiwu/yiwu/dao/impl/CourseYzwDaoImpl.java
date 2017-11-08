package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.CourseYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

@Repository
public class CourseYzwDaoImpl extends BaseDaoImpl<CourseYzw, String> implements CourseYzwDao {

	@Override
	public CourseYzw findOneUnSetSumLessonTimes() throws DataNotFoundException{
		String hql = "FROM CourseYzw WHERE sumLessonTimes is null";
		List<CourseYzw> courses = getSession().createQuery(hql, CourseYzw.class)
				.setMaxResults(1)
				.getResultList();
		if(courses.size()==0)
			throw new DataNotFoundException("all courses were set sumlessonTimes");
		return courses.get(0);
	}

}
