package com.yinzhiwu.yiwu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.LessonYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw.AppointStatus;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.enums.CourseType;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.LessonApiView;
import com.yinzhiwu.yiwu.model.view.PrivateLessonApiView;
import com.yinzhiwu.yiwu.util.CalendarUtil;

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

	@Override
	public List<LessonYzw> findWeeklyLessons(int storeId, CourseType courseType, String teacherName,
			String danceCatagory, Date start, Date end) {
		
		List<String> properties = new ArrayList<>();
		List<Object> values = new ArrayList<>();
		
		StringBuilder hql = new StringBuilder();
//		hql.append("SELECT new com.yinzhiwu.yiwu.model.view.LessonForWeeklyDto");
//		//开始
//		hql.append("(");
//		hql.append("t1.id");
//		hql.append(",t1.name");
//		hql.append(",t1.course.danceDesc");
//		hql.append(",t1.course.danceGrade");
//		hql.append(",t1.course.id");
//		hql.append(",t1.lessonDate");
//		hql.append(",t1.startTime");
//		hql.append(",t1.endTime");
//		hql.append(",t1.store.id");
//		hql.append(",t1.store.name");
//		hql.append(",t1.lessonTime");
//		hql.append(",t1.dueTeacher.id");
//		hql.append(",t1.dueTeacher.name");
//		hql.append(",t1.actualTeacher.id");
//		hql.append(",t1.actualTeacher.name");
//		hql.append(",t1.courseType");
//		hql.append(",t1.subCourseType");
//		hql.append(",t1.lessonStatus");
//		hql.append(",null,null,t1.classRoom.maxStudentCount");
//		hql.append(",t1.appointedStudentCount, null,null,null");
//		hql.append(",null, null, null");
//		//结束
//		hql.append(")");
		hql.append("FROM LessonYzw t1");
		hql.append(" WHERE t1.lessonDate BETWEEN :start and :end" );
		hql.append(" AND t1.courseType <> :privateCourseType");
		properties.add("start");
		properties.add("end");
		properties.add("privateCourseType");
		values.add(start);
		values.add(end);
		values.add(CourseType.PRIVATE);
		if(storeId > 0){
			hql.append(" AND t1.store.id = :storeId");
			properties.add("storeId");
			values.add(storeId);
		}
		if(courseType != null){
			hql.append(" AND t1.courseType = :courseType");
			properties.add("courseType");
			values.add(courseType);
		}
		if(teacherName != null && !"".equals(teacherName.trim())){
			hql.append(" AND (t1.dueTeacherName = :dueTeacherName");
			hql.append(" OR t1.actualTeacherName =:actualTeacherName)");
			properties.add("dueTeacherName");
			properties.add("actualTeacherName");
			values.add(teacherName);
			values.add(teacherName);
		}
		if(danceCatagory != null && !"".equals(danceCatagory)){
			hql.append(" AND t1.name LIKE :fuzzyName");
			properties.add("fuzzyName");
			values.add(danceCatagory);
		}
		hql.append(" ORDER BY t1.lessonDate, t1.startTime");
		
		Query<LessonYzw> query = getSession().createQuery(hql.toString(),LessonYzw.class);
		for(int i = 0; i<properties.size(); i++){
			query.setParameter(properties.get(i), values.get(i));
		}
		
		List<LessonYzw> lessons = query.getResultList();
		if(lessons==null)
			return new ArrayList<>();
		return lessons;
		
		
	}

	@Override
	public Integer findOrderInCourse(LessonYzw lesson) {
		Assert.notNull(lesson, "findOrderInCourse参数lesson不能为空");
		
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT COUNT(1)");
		hql.append(" FROM LessonYzw t1");
		hql.append(" WHERE t1.lessonDate <= :lessonDate");
		hql.append(" AND t1.course.id =:courseId");
		
		String courseId;
		try{
			courseId = lesson.getCourse().getId();
		}catch (RuntimeException e) {
			lesson.setCourse(null);
			return null;
		}
		
		return getSession().createQuery(hql.toString(), Long.class)
				.setParameter("lessonDate", lesson.getLessonDate())
				.setParameter("courseId",courseId)
				.getSingleResult()
				.intValue();
	}

	@Override
	public LessonYzw get(Integer id) {
		LessonYzw lesson = getSession().get(LessonYzw.class, id);
		if(lesson == null) return null;
		
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT COUNT(t1.course)");
		hql.append(" FROM LessonYzw t1");
		hql.append(" WHERE t1.id = :id");
		Long count = getSession().createQuery(hql.toString(), Long.class)
				.setParameter("id", id)
				.getSingleResult();
		if(count ==0 )
			lesson.setCourse(null);
		
		if(null ==lesson.getDueStudentCount())
			lesson.setDueStudentCount(0);
		if(null==lesson.getExperienceStudentCount())
			lesson.setExperienceStudentCount(0);
		if(null==lesson.getRollCalledStudentCount())
			lesson.setRollCalledStudentCount(0);
		if(null==lesson.getActualStudentCount())
			lesson.setActualStudentCount(0);
		if(null==lesson.getCheckedInStudentCount())
			lesson.setCheckedInStudentCount(0);
		if(null==lesson.getAppointedStudentCount())
			lesson.setAppointedStudentCount(0);
		
		return lesson;
	}

	@Override
	public List<PrivateLessonApiView> findPrivateLessonApiViewsByContracNo(String contractNo) {
		Assert.hasLength(contractNo);
		
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT new com.yinzhiwu.yiwu.model.view.PrivateLessonApiView");
		hql.append("(");
		hql.append("t1.id");
		hql.append(",t1.name");
		hql.append(",t1.dueTeacherName");
		hql.append(",t1.lessonDate");
		hql.append(",t1.startTime");
		hql.append(",t1.endTime");
		hql.append(",t2.status");
		hql.append(")");
		hql.append(" FROM LessonYzw t1");
		hql.append(" LEFT JOIN AppointmentYzw t2 WITH t1.id = t2.lesson.id AND t2.contractNo=:AppointedContractNo AND t2.status = :AppointmentStatus");
		hql.append(" WHERE t1.courseType = :privateCourseType");
		hql.append(" AND t1.appointedContract like :contractNo");
//		hql.append(" AND t2.contractNo=:AppointedContractNo");
//		hql.append(" AND t2.status = :AppointmentStatus");
		hql.append(" ORDER BY t1.lessonDate");
		return getSession().createQuery(hql.toString(), PrivateLessonApiView.class)
				.setParameter("privateCourseType", CourseType.PRIVATE)
				.setParameter("contractNo", "%"+contractNo + "%")
				.setParameter("AppointedContractNo", contractNo)
				.setParameter("AppointmentStatus", AppointStatus.APPONTED)
				.getResultList();
	}

	@Override
	public PageBean<LessonApiView> findPageOfClosedLessonApiViewByStoreIdAndLessonDate(Integer storeId, Date date,
			int pageNo, int pageSize) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT new com.yinzhiwu.yiwu.model.view.LessonApiView");
		hql.append("(");
		hql.append("t1.id");
		hql.append(",t1.name");
		hql.append(",t2.id");
		hql.append(",t2.danceDesc");
		hql.append(",t2.danceGrade");
		hql.append(",t1.lessonDate");
		hql.append(",t1.startTime");
		hql.append(",t1.endTime");
		hql.append(",t1.store.id");
		hql.append(",t1.storeName");
		hql.append(",t1.dueTeacherName");
		hql.append(",t1.actualTeacherName");
		hql.append(",t1.connotation.pictureNo");
		hql.append(")");
		hql.append(" FROM LessonYzw t1");
		hql.append(" LEFT JOIN t1.course t2");
		hql.append(" WHERE t1.courseType=:closedCourseType");
		hql.append(" AND t1.store.id =:storeId");
		hql.append(" AND t1.lessonDate = :lessonDate");
		hql.append(" ORDER BY t1.startTime");
		
		return findPage(
				hql.toString(),
				LessonApiView.class,
				new String[]{"closedCourseType", "storeId", "lessonDate"},
				new Object[]{CourseType.CLOSED, storeId, CalendarUtil.getDayBegin(date).getTime()},
				pageNo, 
				pageSize);
	}

	@Override
	public Long findCountByCourseId(String id) {
		return findCountByProperty("course.id", id);
	}

	@Override
	public LessonYzw findByCourseIdAndStartDateTime(String courseId, Date start, Date end) {
		StringBuilder hql = new StringBuilder();
		hql.append(" FROM LessonYzw");
		hql.append(" WHERE course.id = :courseId");
		hql.append(" AND startDateTime BETWEEN :start and :end" );
		List<LessonYzw> lessons =getSession().createQuery(hql.toString(), LessonYzw.class)
			.setParameter("courseId", courseId)
			.setParameter("start", start)
			.setParameter("end", end)
			.getResultList();
		return lessons.size()>0?lessons.get(0):null;
	}

	@Override
	public LessonYzw findByCourseIdAndOrdinalNo(String courseId, Integer ordinalNo) throws DataNotFoundException {
		return findOneByProperties(
				new String[]{"course.id", "ordinalNo"},
				new Object[]{courseId, ordinalNo} );
	}

	@Override
	public List<LessonYzw> findNullOrdinalLessons() {
		String hql = "FROM LessonYzw WHERE ordinalNo is null AND course.id is not null and course.id <> ''"; 
		return getSession().createQuery(hql, LessonYzw.class)
				.getResultList();
	}

	@Override
	public LessonYzw findOneNullOrdinalLessons() {
		String hql = "FROM LessonYzw WHERE ordinalNo is null AND course.id is not null and course.id <> ''";
		List<LessonYzw> lessons =  getSession().createQuery(hql, LessonYzw.class)
					.setMaxResults(1)
					.getResultList();
		if(lessons.size()==0)
			return null;
		return lessons.get(0);
	}

}
