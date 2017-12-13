package com.yinzhiwu.yiwu.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.LessonYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw.AppointStatus;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw.LessonStatus;
import com.yinzhiwu.yiwu.enums.CourseType;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;
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
		criteria.select(builder.construct(LessonApiView.class, 
				lesson.get("id"), 
				lesson.get("name"),
				lesson.get("course").get("id"), 
				lesson.get("course").get("danceDesc"),
				lesson.get("course").get("danceGrade"), 
				lesson.get("lessonDate"),
				lesson.get("startTime"),
				lesson.get("endTime"), 
				lesson.get("storeName"), 
				lesson.get("dueTeacherName")));
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
		List<LessonYzw> lessons = (List<LessonYzw>) getSession().createQuery(builder.toString(),LessonYzw.class)
				.setParameter("courseId", thisLesson.getCourse().getId())
				.setParameter("startDateTime", thisLesson.getStartDateTime())
				.getResultList();
		
		if (lessons != null && lessons.size() == lastN)
			return lessons.get(lastN - 1);
		return null;
	}

	@Override
	public List<LessonYzw> findWeeklyLessons(Integer storeId, CourseType courseType, String teacherName,
			String danceCatagory, Date start, Date end) {
		
		List<String> properties = new ArrayList<>();
		List<Object> values = new ArrayList<>();
		
		StringBuilder hql = new StringBuilder();
		hql.append("FROM LessonYzw t1");
		hql.append(" WHERE t1.lessonDate BETWEEN :start and :end" );
//		hql.append(" AND t1.courseType <> :privateCourseType");
		properties.add("start");
		properties.add("end");
//		properties.add("privateCourseType");
		values.add(start);
		values.add(end);
//		values.add(CourseType.PRIVATE);
		if(null != storeId && storeId > 0){
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
		
		return query.getResultList();
		
		
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
		updateBlankClassRoomId();
		updateBlankCourseId();
		
		LessonYzw lesson = getSession().get(LessonYzw.class, id);
		if(lesson == null) return null;
		
		if(null ==lesson.getDueStudentCount())
			lesson.setDueStudentCount(0);
		if(null==lesson.getExperienceStudentCount())
			lesson.setExperienceStudentCount(0);
		if(null==lesson.getRollCalledStudentCountByCoach())
			lesson.setRollCalledStudentCountByCoach(0);
		if(null==lesson.getActualStudentCount())
			lesson.setActualStudentCount(0);
		if(null==lesson.getCheckedInStudentCount())
			lesson.setCheckedInStudentCount(0);
		if(null==lesson.getAppointedStudentCount())
			lesson.setAppointedStudentCount(0);
		
		return lesson;
	}

	public void updateBlankCourseId(){
//		String hql = "UPDATE LessonYzw SET "
		StringBuilder hql = new StringBuilder();
		hql.append("UPDATE ").append(LessonYzw.class.getSimpleName());
		hql.append(" SET course.id = null");
		hql.append(" WHERE course.id =''");
		
		getSession().createQuery(hql.toString()).executeUpdate();
	}
	
	public void updateBlankClassRoomId(){
		StringBuilder hql = new StringBuilder();
		hql.append("UPDATE ").append(LessonYzw.class.getSimpleName());
		hql.append(" SET classRoom.id = null");
		hql.append(" WHERE classRoom.id =''");
		
		getSession().createQuery(hql.toString()).executeUpdate();
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
		hql.append(" LEFT JOIN LessonAppointmentYzw t2 WITH t1.id = t2.lesson.id AND t2.contractNo=:AppointedContractNo AND t2.status = :AppointmentStatus");
		hql.append(" WHERE t1.courseType = :privateCourseType");
		hql.append(" AND t1.appointedContracts like :contractNo");
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
		String hql = "FROM LessonYzw WHERE ordinalNo is null AND course.id is not null and course.id <> ''"
				+ " ORDER BY lessonDate desc";
		List<LessonYzw> lessons =  getSession().createQuery(hql, LessonYzw.class)
					.setMaxResults(1)
					.getResultList();
		if(lessons.size()==0)
			return null;
		return lessons.get(0);
	}

	@Override
	public List<LessonYzw> findOpenedLessonsOfYesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date start = CalendarUtil.getDayBegin(calendar).getTime();
		Date end = CalendarUtil.getDayEnd(calendar).getTime();
		
		StringBuilder hql = new StringBuilder();
		hql.append("From LessonYzw");
		hql.append(" WHERE lessonDate BETWEEN :start AND :end");
		hql.append(" AND courseType = :opened");
		
		return getSession().createQuery(hql.toString(), LessonYzw.class)
				.setParameter("start", start)
				.setParameter("end", end)
				.setParameter("opened", CourseType.OPENED)
				.getResultList();
	}

	
	@Override
	@Transactional
	@Scheduled(cron="0 20 1 * * ?")
	public void updateZeroActualTeacher(){
		StringBuilder hql = new StringBuilder();
		hql.append("UPDATE LessonYzw set actualTeacher.id = null, actualTeacherName=null WHERE actualTeacher.id = 0");
		getSession().createQuery(hql.toString()).executeUpdate();
	}
	
	
	@Transactional
	@Scheduled(cron="0 10 1 * * ?")
	public void updateCheckedinLessonsStatus(){
		StringBuilder hql = new StringBuilder();
		hql.append("UPDATE LessonYzw SET lessonStatus = :status");
		hql.append(" WHERE lessonDate < :current");
		hql.append(" AND dueTeacher.id IS NOT NULL");
		hql.append(" AND lessonStatus = :unchecked");
		
		getSession().createQuery(hql.toString())
			.setParameter("status", LessonStatus.FINISHED)
			.setParameter("current", CalendarUtil.getDayBegin(Calendar.getInstance()).getTime())
			.setParameter("unchecked", LessonStatus.UN_CHECKED)
			.executeUpdate();
		
	}
	
	@Transactional
	@Scheduled(cron="0 30 5 * * ?")
	public void updateCoachCheckedinStatus(){
		updateUnchecked();
//		updateChecked();
	}
	
	private void updateUnchecked(){
		String sql = "UPDATE vlesson set coachCheckedinStatus = (CASE lessonStatus WHEN 'UN_CHECKED' THEN 'NON_CHECKABLE' ELSE 'UN_CHECKED' END)  WHERE shidaoTeacherId IS NULL;";
		getSession().createNativeQuery(sql).executeUpdate();
	}
	
	private void updateChecked(){
		String sql = "update vlesson t1"
			+ " join vcheck_ins t2 on (t1.id = t2.lesson_id and t1.shidaoTeacherId = t2.teacher_id)"
			+ "	set t1.coachCheckedinStatus = "
			+ "		(case 1 when t1.start_date_time >= t2.sf_create_time then 'CHECKED'"
			+ "				when datediff(t2.sf_create_time, t1.start_date_time) =0 then 'LATE'"
			+ "				ELSE 'PATCHED' "
			+ "				END)"
			+ "	where (t1.coachCheckedinStatus ='UN_CHECKED' OR T1.coachCheckedinStatus IS NULL"
			+ "			OR t1.coachCheckedinStatus = 'NON_CHECKABLE')"
			+ "		and t1.yindaoTeacherId is not NULL;";
		
		getSession().createNativeQuery(sql).executeUpdate();
	}
	
	
}
