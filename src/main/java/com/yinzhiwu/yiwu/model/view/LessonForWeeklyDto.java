package com.yinzhiwu.yiwu.model.view;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw.AppointStatus;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw.LessonStatus;
import com.yinzhiwu.yiwu.enums.CourseType;
import com.yinzhiwu.yiwu.enums.SubCourseType;

import io.swagger.annotations.ApiModelProperty;

/**
*@Author ping
*@Time  创建时间:2017年8月1日下午3:40:04
*
*/

public class LessonForWeeklyDto {
	
	
	public enum CheckedInStatus {
		UN_KNOWN, UN_CHECKED, CHECKED, PATCHED, NON_CHECKABLE
	}
	private Integer id;
	private String name;
	private String danceName;
	private String danceGrade;
	private String courseId;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date lessonDate;
	private Integer week;
	private Time startTime;
	private Time endTime;
	private Integer storeId;
	private String storeName;
	//课程时长
	private Float lessonHours;
	private Integer dueTeacherId;
	private String dueTeacherName;
	private Integer actualTeacherId;
	private String actualTeacherName;
	private CourseType courseType;
	private SubCourseType subCourseType;
	private LessonStatus lessonStatus;
	@ApiModelProperty(value="预约状态",allowableValues="[APPONTED,UN_APOINTED]")
	private AppointStatus appointStatus;
	private CheckedInStatus checkedInStatus;
	@ApiModelProperty(value="可以容纳的最大人数,开放式课程指教室容量, 如果是封闭式课程， 该值为0")
	private Integer maxStudentCount;		//可容纳人数   封闭课程指班级人数， 开放式课程指教室容量
	@ApiModelProperty(value="预约人数, 如果是封闭式课程， 该值为0")
	private Integer appointedStudentCount; //预约人数
	@ApiModelProperty(value="课程班级人数, 如果是开放式课程，该值为0")
	private Integer attendedStudentCount;	//参加人数, 封闭式课的班级人数 
	@ApiModelProperty(value="签到人数")
	private Integer checkedInsStudentCount; //签到人数
	@ApiModelProperty(value="店员点名人数")
	private Integer storeManCallRollCount;  //店员点名人数
	@ApiModelProperty(value="教师点名人数")
	private Integer teacherCallRollCount;   //教师点名人数
	@ApiModelProperty(value="这个课在整套课程中所处的位置")
	private Integer orderInCourse;
	@ApiModelProperty(value="这节课对应的课程所拥有的总课次")
	private Integer sumTimesOfCourse;
	
	
	
	public LessonForWeeklyDto(LessonYzw lesson){
		Assert.notNull(lesson,"传入的参数不能为null");
		
		this.id = lesson.getId();
		this.name = lesson.getName();
		CourseYzw course = lesson.getCourse();
		if(course != null){
			this.danceName = course.getDanceDesc();
			this.danceGrade = course.getDanceGrade();
			this.courseId = course.getId();
		}
		this.lessonDate = lesson.getLessonDate();
		this.week = getWeek(this.lessonDate);
		this.startTime = lesson.getStartTime();
		this.endTime = lesson.getEndTime();
		if(lesson.getStore() != null){
			this.storeId = lesson.getStore().getId();
			this.storeName = lesson.getStore().getName();
		}
		this.lessonHours = lesson.getLessonTime();
		if(lesson.getDueTeacher() != null){
			this.dueTeacherId = lesson.getDueTeacher().getId();
			this.dueTeacherName = lesson.getDueTeacher().getName();
		}
		if(lesson.getActualTeacher() != null){
			this.actualTeacherId = lesson.getActualTeacher().getId();
			this.actualTeacherName = lesson.getActualTeacher().getName();
		}
		this.courseType = lesson.getCourseType();
		this.subCourseType = lesson.getSubCourseType();
		this.lessonStatus = lesson.getLessonStatus();
		//TOD this.attendedStatus
		//TOD this.checkedInStatus
		if(lesson.getCourseType() == CourseType.OPENED
				&& lesson.getClassRoom() != null)
			this.maxStudentCount = lesson.getClassRoom().getMaxStudentCount();
		//TODO this.appointedStudentCount = lesson.getAppointedStudentCount();
		if(CourseType.CLOSED == lesson.getCourseType()
				&& course != null){
			this.attendedStudentCount = course.getStudentCount();
		}
		 this.checkedInsStudentCount = lesson.getCheckedInStudentCount();
		//TOD storeManCallRollCount
		 this.teacherCallRollCount = lesson.getRollCalledStudentCount();
		//TOD  orderInCourse
		//TOD  sumTimesOfCourse
		
	}
	public LessonForWeeklyDto() {
	}
	
	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDanceName() {
		return danceName;
	}

	public String getDanceGrade() {
		return danceGrade;
	}

	public String getCourseId() {
		return courseId;
	}

	public Date getLessonDate() {
		return lessonDate;
	}

	public Integer getWeek() {
		return week;
	}

	public Time getStartTime() {
		return startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public Float getLessonHours() {
		return lessonHours;
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

	public CourseType getCourseType() {
		return courseType;
	}

	public SubCourseType getSubCourseType() {
		return subCourseType;
	}

	public LessonStatus getLessonStatus() {
		return lessonStatus;
	}

	public Integer getMaxStudentCount() {
		return maxStudentCount;
	}

	public CheckedInStatus getCheckedInStatus() {
		return checkedInStatus;
	}

	public Integer getAppointedStudentCount() {
		return appointedStudentCount;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDanceName(String danceName) {
		this.danceName = danceName;
	}

	public void setDanceGrade(String danceGrade) {
		this.danceGrade = danceGrade;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public void setLessonDate(Date lessonDate) {
		this.lessonDate = lessonDate;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setLessonHours(Float lessonHours) {
		this.lessonHours = lessonHours;
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

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public void setSubCourseType(SubCourseType subCourseType) {
		this.subCourseType = subCourseType;
	}

	public void setLessonStatus(LessonStatus lessonStatus) {
		this.lessonStatus = lessonStatus;
	}

	public void setMaxStudentCount(Integer maxStudentCount) {
		this.maxStudentCount = maxStudentCount;
	}

	public void setCheckedInStatus(CheckedInStatus checkedInStatus) {
		this.checkedInStatus = checkedInStatus;
	}

	public void setAppointedStudentCount(Integer appointedStudentCount) {
		this.appointedStudentCount = appointedStudentCount;
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

	
	private int getWeek(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	public AppointStatus getAppointStatus() {
		return appointStatus;
	}
	public void setAppointStatus(AppointStatus appointStatus) {
		this.appointStatus = appointStatus;
	}
	public LessonForWeeklyDto(Integer id, String name, String danceName, String danceGrade, String courseId,
			Date lessonDate, Integer week, Time startTime, Time endTime, Integer storeId, String storeName,
			Float lessonHours, Integer dueTeacherId, String dueTeacherName, Integer actualTeacherId,
			String actualTeacherName, CourseType courseType, SubCourseType subCourseType, LessonStatus lessonStatus,
			AppointStatus appointStatus, CheckedInStatus checkedInStatus, Integer maxStudentCount,
			Integer appointedStudentCount, Integer attendedStudentCount, Integer checkedInsStudentCount,
			Integer storeManCallRollCount, Integer teacherCallRollCount, Integer orderInCourse,
			Integer sumTimesOfCourse) {
		this.id = id;
		this.name = name;
		this.danceName = danceName;
		this.danceGrade = danceGrade;
		this.courseId = courseId;
		this.lessonDate = lessonDate;
//		this.week = week;
		this.startTime = startTime;
		this.endTime = endTime;
		this.storeId = storeId;
		this.storeName = storeName;
		this.lessonHours = lessonHours;
		this.dueTeacherId = dueTeacherId;
		this.dueTeacherName = dueTeacherName;
		this.actualTeacherId = actualTeacherId;
		this.actualTeacherName = actualTeacherName;
		this.courseType = courseType;
		this.subCourseType = subCourseType;
		this.lessonStatus = lessonStatus;
		this.appointStatus = appointStatus;
		this.checkedInStatus = checkedInStatus;
		this.maxStudentCount = maxStudentCount;
		this.appointedStudentCount = appointedStudentCount;
		this.attendedStudentCount = attendedStudentCount;
		this.checkedInsStudentCount = checkedInsStudentCount;
		this.storeManCallRollCount = storeManCallRollCount;
		this.teacherCallRollCount = teacherCallRollCount;
		this.orderInCourse = orderInCourse;
		this.sumTimesOfCourse = sumTimesOfCourse;
	}
	
}
