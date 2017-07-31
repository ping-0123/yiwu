package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.LessonYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.model.view.LessonApiView;

@Repository
public class LessonYzwDaoImpl extends BaseDaoImpl<LessonYzw, Integer> implements LessonYzwDao {

	@Override
	public List<LessonYzw> findByCourseId(String courseId) {
		
			return  findByProperty("course.id", courseId);
	}

	@Override
	public List<LessonApiView> findApiViewsByCourseId(String courseId) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<LessonApiView> criteria = builder.createQuery(LessonApiView.class);
		// CriteriaQuery<LessonApiView> criteria = new
		// LessonApiView().getDtoCriteria(getSession());
		Root<?> lesson = criteria.from(LessonYzw.class);
		criteria.select(builder.construct(LessonApiView.class, lesson.get("id"), lesson.get("name"),
				lesson.get("course").get("id"), lesson.get("course").get("danceDesc"),
				lesson.get("course").get("danceGrade"), lesson.get("lessonDate"), lesson.get("startTime"),
				lesson.get("endTime"), lesson.get("storeName"), lesson.get("dueTeacherName")));
		Predicate predicate = builder.equal(lesson.get("course").get("id"), courseId);
		criteria.where(predicate);
		criteria.orderBy(builder.desc(lesson.get("lessonDate")));
		return getSession().createQuery(criteria).getResultList();
	}

	@Override
	public LessonYzw findLastNLesson(LessonYzw thisLesson, int lastN) {
		if (lastN == 0)
			return thisLesson;
		if (thisLesson == null)
			return null;
		if (thisLesson.getCourse() == null)
			return null;

		StringBuilder builder = new StringBuilder();
		if (lastN > 0) {
			builder.append("FROM LessonYzw WHERE startDateTime <:startDateTime and courseid = :courseId");
			builder.append(" order by startDateTime desc");
		} else {
			builder.append("FROM LessonYzw WHERE startDateTime >:startDateTime and courseid = :courseId");
			builder.append(" order by startDateTime");
		}
		getHibernateTemplate().setMaxResults(lastN);
		@SuppressWarnings("unchecked")
		List<LessonYzw> lessons = (List<LessonYzw>) getHibernateTemplate().findByNamedParam(builder.toString(),
				new String[] { "startDateTime", "courseId" },
				new Object[] { thisLesson.getStartDateTime(), thisLesson.getCourse().getId() });
		if (lessons != null && lessons.size() == lastN)
			return lessons.get(lastN - 1);
		return null;
	}

}
