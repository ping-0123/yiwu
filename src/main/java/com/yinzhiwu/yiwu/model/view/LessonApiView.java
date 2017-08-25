package com.yinzhiwu.yiwu.model.view;

import java.sql.Time;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;

@JsonInclude(value= Include.NON_NULL)
public class LessonApiView implements DtoCriteria<LessonYzw> {

	private static final Log logger = LogFactory.getLog(LessonApiView.class);

	private Integer id;
	private String name;
	private String courseId;
	private String danceName;
	private String danceGrade;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date date;
	@JsonFormat(pattern = "HH:mm")
	private Time start;
	@JsonFormat(pattern = "HH:mm")
	private Time end;
	private Integer storeId;
	private String storeName;
	private String dueTeacher;
	private String actualTeacher;
	private String pictureUrl;

	public LessonApiView() {
	}

	public LessonApiView(Integer id, String name, Date date, String teacherName, String storeName){
		this.id = id;
		this.name = name==null?"":name;
		this.date = date;
		this.dueTeacher = teacherName==null?"":teacherName;
		this.storeName = storeName==null?"":storeName;
	}
	public LessonApiView(LessonYzw l) {
		this.id = l.getId();
		this.name = l.getName();
		this.date = l.getLessonDate();
		this.start = l.getStartTime();
		this.end = l.getEndTime();
		this.storeName = l.getStoreName();
		this.dueTeacher = l.getDueTeacherName();
		try {
			CourseYzw course = l.getCourse();
			this.courseId = course.getId();
			this.danceName = course.getDanceDesc();
			this.danceGrade = course.getDanceGrade();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public Integer getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public Time getStart() {
		return start;
	}

	public Time getEnd() {
		return end;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getDueTeacher() {
		return dueTeacher;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setStart(Time start) {
		this.start = start;
	}

	public void setEnd(Time end) {
		this.end = end;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setDueTeacher(String dueTeacher) {
		this.dueTeacher = dueTeacher;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDanceName() {
		return danceName;
	}

	public void setDanceName(String danceName) {
		this.danceName = danceName;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getDanceGrade() {
		return danceGrade;
	}

	public void setDanceGrade(String danceGrade) {
		this.danceGrade = danceGrade;
	}

	@Override
	public CriteriaQuery<LessonApiView> getDtoCriteria(Session session, Class<?> sourceEntityClass) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<LessonApiView> criteria = builder.createQuery(LessonApiView.class);
		Root<?> lesson = criteria.from(sourceEntityClass);
		criteria.select(builder.construct(LessonApiView.class, lesson.get("id"), lesson.get("name"),
				lesson.get("course").get("id"), lesson.get("course").get("danceDesc"),
				lesson.get("course").get("danceGrade"), lesson.get("lessonDate"), lesson.get("startTime"),
				lesson.get("endTime"), lesson.get("storeName"), lesson.get("dueTeacherName")));
		return criteria;
	}

	@Override
	public CriteriaQuery<LessonApiView> getDtoCriteria(Session session) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<LessonApiView> criteria = builder.createQuery(LessonApiView.class);
		Root<?> lesson = criteria.from(LessonYzw.class);
		criteria.select(builder.construct(LessonApiView.class, lesson.get("id"), lesson.get("name"),
				lesson.get("course").get("id"), lesson.get("course").get("danceDesc"),
				lesson.get("course").get("danceGrade"), lesson.get("lessonDate"), lesson.get("startTime"),
				lesson.get("endTime"), lesson.get("storeName"), lesson.get("dueTeacherName")));
		return criteria;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LessonApiView(Integer id, String name, String courseId, String danceName, String danceGrade, Date date,
			Date start, Date end, String storeName, String dueTeacher) {
		this.id = id;
		this.name = name;
		this.courseId = courseId;
		this.danceName = danceName;
		this.danceGrade = danceGrade;
		this.date = date;
		this.start = (Time) start;
		this.end = (Time) end;
		this.storeName = storeName;
		this.dueTeacher = dueTeacher;
	}

	public String getActualTeacher() {
		return actualTeacher;
	}

	public void setActualTeacher(String actualTeacher) {
		this.actualTeacher = actualTeacher;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public LessonApiView(Integer id, String name, String courseId, String danceName, String danceGrade, Date date,
			Date start, Date end, Integer storeId, String storeName, String dueTeacher, String actualTeacher,
			String pictureUrl) {
		this.id = id;
		this.name = name;
		this.courseId = courseId;
		this.danceName = danceName;
		this.danceGrade = danceGrade;
		this.date = date;
		this.start = (Time) start;
		this.end = (Time) end;
		this.storeId = storeId;
		this.storeName = storeName;
		this.dueTeacher = dueTeacher;
		this.actualTeacher = actualTeacher;
		this.pictureUrl = pictureUrl;
	}

	
}
