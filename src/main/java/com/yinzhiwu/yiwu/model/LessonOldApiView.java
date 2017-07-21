package com.yinzhiwu.yiwu.model;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.yzwOld.Lesson;

public class LessonOldApiView {
	
//	public static String  APPONTED = "已预约"; // 已预约
//	public static String UN_APOINTED = "未预约";
//	public static String ATTENDED = "已排班";
	
	public enum AttendedStatus{UNKNOWN,APPONTED,UN_APOINTED,ATTENDED}
	
	public enum LessonStatus{UN_KNOWN,UN_AUDITED, AUDITED, FINISHED}
	
	public enum CheckedInStatus{UN_KNOWN, UN_CHECKED, CHECKED,PATCHED,NON_CHECKABLE}
	
	private Integer lessonId;
	
	private String courseid;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
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
	
	private Integer actualTeacherId;
	
	private String actualTeacherName;
	
	private String courseType;
	
	private String subCourseType;
	
	private LessonStatus lessonStatus;

	private Integer maxStudentCount;
	
	private AttendedStatus attendedStatus;
	
	private CheckedInStatus checkedInStatus;
	
	private Integer appointedStudentCount;
	
	private String danceName;
	
	private String danceGrade;
	
	private Integer attendedStudentCount;
	
	private Integer checkedInsStudentCount;
	
	private Integer storeManCallRollCount;
	
	private Integer teacherCallRollCount;
	
	//这个课在整套课程中所处的位置
	private Integer orderInCourse;
	
	//这节课对应的课程所拥有的总课次
	private Integer sumTimesOfCourse;
	

	public LessonOldApiView(Integer lessonId, String courseid, Date lessonDate, Time startTime, Time endTime,
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
	
	
	public LessonOldApiView(Lesson l){
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
		this.actualTeacherId = l.getActualTeacherId();
		this.actualTeacherName = l.getActualTeacherName();
		this.dueTeacherName = l.getDueTeacherName();
		this.courseType = l.getCourseType();
		this.subCourseType = l.getSubCourseType();
//		System.out.println(l.getSubCourseType());
		switch (l.getLessonStatus()) {
		case "":
			this.lessonStatus = LessonStatus.UN_KNOWN;
			break;
		case "未审核":
			this.lessonStatus = LessonStatus.UN_AUDITED;
			break;
		case "已排课":
			this.lessonStatus = LessonStatus.AUDITED;
			break;
		case "已开课":
			this.lessonStatus =LessonStatus.FINISHED;
			break;
		default:
			this.lessonStatus = LessonStatus.UN_KNOWN;
			break;
		}
		setWeek();
		this.attendedStatus = AttendedStatus.UNKNOWN;
//		this.sumTimesOfCourse = lessonDao.findCountByProperty("courseid", this.courseid);
//		this.orderInCourse = lessonDao.findOrderInCourse(l);
	}
	

	

	private  void setWeek(){
		Calendar ca = Calendar.getInstance();
		ca.setTime(lessonDate);
		week = ca.get(Calendar.DAY_OF_WEEK);
	}


	public Integer getLessonId() {
		return lessonId;
	}


	public String getCourseid() {
		return courseid;
	}


	public Date getLessonDate() {
		return lessonDate;
	}


	public int getWeek() {
		return week;
	}


	public Time getStartTime() {
		return startTime;
	}


	public Time getEndTime() {
		return endTime;
	}


	public String getLessonDesc() {
		return lessonDesc;
	}


	public Integer getStoreId() {
		return storeId;
	}


	public String getStoreName() {
		return storeName;
	}


	public Float getLessonTime() {
		return lessonTime;
	}


	public Integer getDueTeacherId() {
		return dueTeacherId;
	}


	public String getDueTeacherName() {
		return dueTeacherName;
	}


	public Integer getActualTeacherId() {
		return actualTeacherId;
	}


	public String getActualTeacherName() {
		return actualTeacherName;
	}


	public String getCourseType() {
		return courseType;
	}


	public String getSubCourseType() {
		return subCourseType;
	}


	public LessonStatus getLessonStatus() {
		return lessonStatus;
	}


	public Integer getMaxStudentCount() {
		return maxStudentCount;
	}


	public AttendedStatus getAttendedStatus() {
		return attendedStatus;
	}


	public CheckedInStatus getCheckedInStatus() {
		return checkedInStatus;
	}


	public Integer getAppointedStudentCount() {
		return appointedStudentCount;
	}


	public String getDanceName() {
		return danceName;
	}


	public String getDanceGrade() {
		return danceGrade;
	}


	public Integer getAttendedStudentCount() {
		return attendedStudentCount;
	}


	public Integer getCheckedInsStudentCount() {
		return checkedInsStudentCount;
	}


	public Integer getStoreManCallRollCount() {
		return storeManCallRollCount;
	}


	public Integer getTeacherCallRollCount() {
		return teacherCallRollCount;
	}


	public Integer getOrderInCourse() {
		return orderInCourse;
	}


	public Integer getSumTimesOfCourse() {
		return sumTimesOfCourse;
	}


	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}


	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}


	public void setLessonDate(Date lessonDate) {
		this.lessonDate = lessonDate;
	}


	public void setWeek(int week) {
		this.week = week;
	}


	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}


	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}


	public void setLessonDesc(String lessonDesc) {
		this.lessonDesc = lessonDesc;
	}


	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	public void setLessonTime(Float lessonTime) {
		this.lessonTime = lessonTime;
	}


	public void setDueTeacherId(Integer dueTeacherId) {
		this.dueTeacherId = dueTeacherId;
	}


	public void setDueTeacherName(String dueTeacherName) {
		this.dueTeacherName = dueTeacherName;
	}


	public void setActualTeacherId(Integer actualTeacherId) {
		this.actualTeacherId = actualTeacherId;
	}


	public void setActualTeacherName(String actualTeacherName) {
		this.actualTeacherName = actualTeacherName;
	}


	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}


	public void setSubCourseType(String subCourseType) {
		this.subCourseType = subCourseType;
	}


	public void setLessonStatus(LessonStatus lessonStatus) {
		this.lessonStatus = lessonStatus;
	}


	public void setMaxStudentCount(Integer maxStudentCount) {
		this.maxStudentCount = maxStudentCount;
	}


	public void setAttendedStatus(AttendedStatus attendedStatus) {
		this.attendedStatus = attendedStatus;
	}


	public void setCheckedInStatus(CheckedInStatus checkedInStatus) {
		this.checkedInStatus = checkedInStatus;
	}


	public void setAppointedStudentCount(Integer appointedStudentCount) {
		this.appointedStudentCount = appointedStudentCount;
	}


	public void setDanceName(String danceName) {
		this.danceName = danceName;
	}


	public void setDanceGrade(String danceGrade) {
		this.danceGrade = danceGrade;
	}


	public void setAttendedStudentCount(Integer attendedStudentCount) {
		this.attendedStudentCount = attendedStudentCount;
	}


	public void setCheckedInsStudentCount(Integer checkedInsStudentCount) {
		this.checkedInsStudentCount = checkedInsStudentCount;
	}


	public void setStoreManCallRollCount(Integer storeManCallRollCount) {
		this.storeManCallRollCount = storeManCallRollCount;
	}


	public void setTeacherCallRollCount(Integer teacherCallRollCount) {
		this.teacherCallRollCount = teacherCallRollCount;
	}


	public void setOrderInCourse(Integer orderInCourse) {
		this.orderInCourse = orderInCourse;
	}


	public void setSumTimesOfCourse(Integer sumTimesOfCourse) {
		this.sumTimesOfCourse = sumTimesOfCourse;
	}

	
	
}
