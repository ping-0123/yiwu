package com.yinzhiwu.springmvc3.model;

import java.util.Date;
import java.sql.Time;
import java.util.Calendar;

import com.yinzhiwu.springmvc3.entity.Lesson;

public class MiniLesson {
	
//	public static String  APPONTED = "已预约"; // 已预约
//	public static String UN_APOINTED = "未预约";
//	public static String ATTENDED = "已排班";
	
	public enum AttendedStatus{UNKNOWN,APPONTED,UN_APOINTED,ATTENDED}
	
	private Integer lessonId;
	
	private String courseid;
	
	private Date lessonDate;
	
	private int week;
	
	private Time startTime;
	
	private Time endTime;
	
	private String lessonDesc;
	
	private Integer storeId;
	
	private String storeName;
	
	private Float lessonTime;
	
	private Integer dueTeacherId;
	
	private String dueTeacherName;
	
	private String courseType;
	
	private String subCourseType;

	private Integer maxStudentCount;
	
	private AttendedStatus attendedStatus;
	
	private int appointedStudentCount;
	
	private String danceName;
	
	private String danceGrade;
	
	private int attendedStudentCount;
	
	private int checkedInsStudentCount;
	


	public MiniLesson(Integer lessonId, String courseid, Date lessonDate, Time startTime, Time endTime,
			String lessonDesc, Integer storeId, String storeName, Float lessonTime, Integer dueTeacherId,
			String dueTeacherName, String courseType, String subCourseType) {
		super();
		this.lessonId = lessonId;
		this.courseid = courseid;
		this.lessonDate = lessonDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.lessonDesc = lessonDesc;
		this.storeId = storeId;
		this.storeName = storeName;
		this.lessonTime = lessonTime;
		this.dueTeacherId = dueTeacherId;
		this.dueTeacherName = dueTeacherName;
		this.courseType = courseType;
		this.subCourseType = subCourseType;
		setWeek();
		this.attendedStatus = AttendedStatus.UNKNOWN;
		
	}
	
	
	public MiniLesson(Lesson l){
		this.lessonId = l.getLessonId();
		this.courseid = l.getCourseid();
		this.lessonDate = l.getLessonDate();
		this.startTime = l.getStartTime();
		this.endTime = l.getEndTime();
		this.lessonDesc = l.getLessonDesc();
		this.storeId = l.getStoreId();
		this.storeName = l.getStoreName();
		this.lessonTime = l.getLessonTime();
		this.dueTeacherId = l.getDueTeacherId();
		this.dueTeacherName = l.getDueTeacherName();
		this.courseType = l.getCourseType();
		this.subCourseType = l.getSubCourseType();
		setWeek();
		this.attendedStatus = AttendedStatus.UNKNOWN;
	}
	

	public final Integer getLessonId() {
		return lessonId;
	}

	public final void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}

	public final String getCourseid() {
		return courseid;
	}

	public final void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public final Date getLessonDate() {
		return lessonDate;
	}

	public final void setLessonDate(Date lessonDate) {
		this.lessonDate = lessonDate;
		setWeek();
	}

	public final int getWeek() {
		return week;
	}

	private final void setWeek(){
		Calendar ca = Calendar.getInstance();
		ca.setTime(lessonDate);
		week = ca.get(Calendar.DAY_OF_WEEK);
	}

	public final Time getStartTime() {
		return startTime;
	}

	public final void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public final Time getEndTime() {
		return endTime;
	}

	public final void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public final String getLessonDesc() {
		return lessonDesc;
	}

	public final void setLessonDesc(String lessonDesc) {
		this.lessonDesc = lessonDesc;
	}

	public final Integer getStoreId() {
		return storeId;
	}

	public final void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public final String getStoreName() {
		return storeName;
	}

	public final void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public final Float getLessonTime() {
		return lessonTime;
	}

	public final void setLessonTime(Float lessonTime) {
		this.lessonTime = lessonTime;
	}

	public final Integer getDueTeacherId() {
		return dueTeacherId;
	}

	public final void setDueTeacherId(Integer dueTeacherId) {
		this.dueTeacherId = dueTeacherId;
	}

	public final String getDueTeacherName() {
		return dueTeacherName;
	}

	public final void setDueTeacherName(String dueTeacherName) {
		this.dueTeacherName = dueTeacherName;
	}

	public final String getCourseType() {
		return courseType;
	}

	public final void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public final String getSubCourseType() {
		return subCourseType;
	}

	public final void setSubCourseType(String subCourseType) {
		this.subCourseType = subCourseType;
	}


	public Integer getMaxStudentCount() {
		return maxStudentCount;
	}


	public void setMaxStudentCount(Integer maxStudentCount) {
		this.maxStudentCount = maxStudentCount;
	}


	public final AttendedStatus getAttendedStatus() {
		return attendedStatus;
	}


	public final void setAttendedStatus(AttendedStatus attendedStatus) {
		this.attendedStatus = attendedStatus;
	}


	public int getAppointedStudentCount() {
		return appointedStudentCount;
	}


	public void setAppointedStudentCount(int appointedStudentCount) {
		this.appointedStudentCount = appointedStudentCount;
	}


	public String getDanceName() {
		return danceName;
	}


	public void setDanceName(String danceName) {
		this.danceName = danceName;
	}


	public String getDanceGrade() {
		return danceGrade;
	}


	public void setDanceGrade(String danceGrade) {
		this.danceGrade = danceGrade;
	}
	public final int getAttendedStudentCount() {
		return attendedStudentCount;
	}


	public final void setAttendedStudentCount(int attendedStudentCount) {
		this.attendedStudentCount = attendedStudentCount;
	}


	public final int getCheckedInsStudentCount() {
		return checkedInsStudentCount;
	}


	public final void setCheckedInsStudentCount(int checkedInsStudentCount) {
		this.checkedInsStudentCount = checkedInsStudentCount;
	}


	public final void setWeek(int week) {
		this.week = week;
	}

	
}
