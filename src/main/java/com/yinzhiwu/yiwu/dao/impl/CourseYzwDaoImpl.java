package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.CourseYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;

@Repository
public class CourseYzwDaoImpl extends BaseDaoImpl<CourseYzw, String> implements CourseYzwDao {

	@Override
	public List<CourseYzw> find100UnSetSumLessonTimes(){
		String hql = "FROM CourseYzw WHERE sumLessonTimes is null OR sumLessonTimes=0 ORDER BY createTime DESC";
		return getSession().createQuery(hql, CourseYzw.class)
				.setMaxResults(100)
				.getResultList();
	}

}
