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

	@Override
	public LessonYzw findLastNLesson(LessonYzw thisLesson, int lastN) {
		if(lastN==0) return thisLesson;
		if(thisLesson == null) return null;
		if(thisLesson.getCourse() == null) return null;
		
		StringBuilder builder = new StringBuilder();
		if(lastN > 0){
			builder.append("FROM LessonYzw WHERE startDateTime <:startDateTime and courseid = :courseId");
			builder.append(" order by startDateTime desc");
		}else{
			builder.append("FROM LessonYzw WHERE startDateTime >:startDateTime and courseid = :courseId");
			builder.append(" order by startDateTime");
		}
		getHibernateTemplate().setMaxResults(lastN);
		@SuppressWarnings("unchecked")
		List<LessonYzw> lessons = (List<LessonYzw>) getHibernateTemplate().findByNamedParam(
				builder.toString(), 
				new String[]{"startDateTime", "courseId"}, 
				new Object[]{thisLesson.getStartDateTime(), thisLesson.getCourse().getId()});
		if(lessons != null && lessons.size() ==lastN)
			return lessons.get(lastN-1);
		return null;
	}
		

}
