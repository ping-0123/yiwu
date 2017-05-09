package com.yinzhiwu.springmvc3.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vcourse")
public class Course {
	
	@Id
	@Column(length=32)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String courseId;
	
	@Column(length=128, name="name")
	private String courseDesc;
	
	@Column(length =32, name="dance_id")
	private String danceId;
	

	@Column(length =32)
	private String danceDesc;
	
	@Column(name="store_id")
	private Integer storeId;
	
	@Column(length=32)
	private String storeName;
	
	@Column(name="teacher_id")
	private Integer teacherId;
	
	@Column(length=32)
	private String teacherName;
	
	@Column(length=32, name="interval_id")
	private String intervalId;
	
	@Column(length=32)
	private String intervalDesc;
	
	@Column
	private Time startTime;
	
	@Column
	private Time endTime;
	
	@Column
	private Date startDate;
	
	@Column
	private Date endDate;
	
	@Column
	private Float sumCourseHours;
	
	@Column(length=32, name="classRoom_id")
	private String classRoomId;
	
	@Column(length=32)
	private String classRoomName;
	
	@Column(name="sf_create_user")
	private Integer createUserId;
	
	@Column(name="sf_last_change_user")
	private Integer lastChangeUserId;
	
	@Column(name="sf_create_time")
	private Date createTime;
	
	@Column(name="sf_last_change_time")
	private Date lastChangeTime;
	
	@Column
	private Integer machineCode;
	
	@Column(name="sf_last_sync_timeStamp")
	private Date lastSyncTimeStamp;
	
	@Column(name="weeklyCourseHours")
	private Float weekCourseHours;
	
	@Column(name="delete_flag")
	private Integer isDeleted;
	
	@Column(name="weeks", length=32)
	private String weekes;
	
	@Column(length=32)
	private String courseType;
	
	@Column(length=32)
	private String subCourseType;
	
	
	@Column(length=32,name="monInterval_id")
	private String monIntervalId;
	
	@Column(length=32, name="monRoom_id")
	private String monRoomId;
	
	@Column(length=32, name="tueInterval_id")
	private String tueIntervalId;
	
	@Column(length=32, name="tueRoom_id")
	private String tueRoomId;
	
	@Column(length=32, name="wedInterval_id")
	private String wedIntervalId;
	
	@Column(length=32, name="wedRoom_id")
	private String wedRoomId;
	
	@Column(length=32, name="tueInterval_id")
	private String thuIntervalId;
	
	@Column(length=32, name="thuRoom_id")
	private String thuRoomId;
	
	@Column(length=32, name="friInterval_id")
	private String friIntervalId;
	
	@Column(length=32, name="friRoom_id")
	private String friRoomId;
	
	@Column(length=32, name="satInterval_id")
	private String satIntervalId;
	
	@Column(length=32, name="satRoom_id")
	private String satRoomId;
	
	@Column(length=32, name="sunInterval_id")
	private String sunIntervalId;
	
	@Column(length=32, name="sunRoom_id")
	private String sunRoomId;
	
	@Column(length=32, name="status")
	private String courseStatus;
	
	@Column
	private Integer studentCount;
	
	@Column(name="sf_last_change_timeStamp")
	private Date lastChangeTimeStamp;
	
	@Column(name="include_holeday_flag")
	private Integer isHolidayIncluded;
	
	@Column(length=32)
	private String danceGrade;
	
	@Column(name="ShenheRen")
	private Integer auditorId;
	
	@Column
	private String connotation;
	
	@Column(name="help")
	private String helpInformation;
	
	@Column
	private String briefIntroduction;
	
	@Column(name="picture")
	private String pictureNo;
	
	@Column
	private String videoUrl;
	
	@Column(name="audio")
	private String audioName;
	
	@Column
	private String audioUrl;
	
	@Column
	private String danceIntroduction;

	public final String getCourseId() {
		return courseId;
	}

	public final void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public final String getCourseDesc() {
		return courseDesc;
	}

	public final void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}

	public final String getDanceId() {
		return danceId;
	}

	public final void setDanceId(String danceId) {
		this.danceId = danceId;
	}

	public final String getDanceDesc() {
		return danceDesc;
	}

	public final void setDanceDesc(String danceDesc) {
		this.danceDesc = danceDesc;
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

	public final Integer getTeacherId() {
		return teacherId;
	}

	public final void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public final String getTeacherName() {
		return teacherName;
	}

	public final void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public final String getIntervalId() {
		return intervalId;
	}

	public final void setIntervalId(String intervalId) {
		this.intervalId = intervalId;
	}

	public final String getIntervalDesc() {
		return intervalDesc;
	}

	public final void setIntervalDesc(String intervalDesc) {
		this.intervalDesc = intervalDesc;
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

	public final Date getStartDate() {
		return startDate;
	}

	public final void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public final Date getEndDate() {
		return endDate;
	}

	public final void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public final Float getSumCourseHours() {
		return sumCourseHours;
	}

	public final void setSumCourseHours(Float sumCourseHours) {
		this.sumCourseHours = sumCourseHours;
	}

	public final String getClassRoomId() {
		return classRoomId;
	}

	public final void setClassRoomId(String classRoomId) {
		this.classRoomId = classRoomId;
	}

	public final String getClassRoomName() {
		return classRoomName;
	}

	public final void setClassRoomName(String classRoomName) {
		this.classRoomName = classRoomName;
	}

	public final Integer getCreateUserId() {
		return createUserId;
	}

	public final void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public final Integer getLastChangeUserId() {
		return lastChangeUserId;
	}

	public final void setLastChangeUserId(Integer lastChangeUserId) {
		this.lastChangeUserId = lastChangeUserId;
	}

	public final Date getCreateTime() {
		return createTime;
	}

	public final void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public final Date getLastChangeTime() {
		return lastChangeTime;
	}

	public final void setLastChangeTime(Date lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
	}

	public final Integer getMachineCode() {
		return machineCode;
	}

	public final void setMachineCode(Integer machineCode) {
		this.machineCode = machineCode;
	}

	public final Date getLastSyncTimeStamp() {
		return lastSyncTimeStamp;
	}

	public final void setLastSyncTimeStamp(Date lastSyncTimeStamp) {
		this.lastSyncTimeStamp = lastSyncTimeStamp;
	}

	public final Float getWeekCourseHours() {
		return weekCourseHours;
	}

	public final void setWeekCourseHours(Float weekCourseHours) {
		this.weekCourseHours = weekCourseHours;
	}

	public final Integer getIsDeleted() {
		return isDeleted;
	}

	public final void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public final String getWeekes() {
		return weekes;
	}

	public final void setWeekes(String weekes) {
		this.weekes = weekes;
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

	

	public final String getWedIntervalId() {
		return wedIntervalId;
	}

	public final void setWedIntervalId(String wedIntervalId) {
		this.wedIntervalId = wedIntervalId;
	}

	public final String getWedRoomId() {
		return wedRoomId;
	}

	public final void setWedRoomId(String wedRoomId) {
		this.wedRoomId = wedRoomId;
	}

	public final String getThuIntervalId() {
		return thuIntervalId;
	}

	public final void setThuIntervalId(String thuIntervalId) {
		this.thuIntervalId = thuIntervalId;
	}

	public final String getThuRoomId() {
		return thuRoomId;
	}

	public final void setThuRoomId(String thuRoomId) {
		this.thuRoomId = thuRoomId;
	}

	public final String getFriIntervalId() {
		return friIntervalId;
	}

	public final void setFriIntervalId(String friIntervalId) {
		this.friIntervalId = friIntervalId;
	}

	public final String getFriRoomId() {
		return friRoomId;
	}

	public final void setFriRoomId(String friRoomId) {
		this.friRoomId = friRoomId;
	}

	public final String getSatIntervalId() {
		return satIntervalId;
	}

	public final void setSatIntervalId(String satIntervalId) {
		this.satIntervalId = satIntervalId;
	}

	public final String getSatRoomId() {
		return satRoomId;
	}

	public final void setSatRoomId(String satRoomId) {
		this.satRoomId = satRoomId;
	}

	public final String getSunIntervalId() {
		return sunIntervalId;
	}

	public final void setSunIntervalId(String sunIntervalId) {
		this.sunIntervalId = sunIntervalId;
	}

	public final String getSunRoomId() {
		return sunRoomId;
	}

	public final void setSunRoomId(String sunRoomId) {
		this.sunRoomId = sunRoomId;
	}

	public final String getCourseStatus() {
		return courseStatus;
	}

	public final void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}

	public final Integer getStudentCount() {
		return studentCount;
	}

	public final void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}

	public final Date getLastChangeTimeStamp() {
		return lastChangeTimeStamp;
	}

	public final void setLastChangeTimeStamp(Date lastChangeTimeStamp) {
		this.lastChangeTimeStamp = lastChangeTimeStamp;
	}

	public final Integer getIsHolidayIncluded() {
		return isHolidayIncluded;
	}

	public final void setIsHolidayIncluded(Integer isHolidayIncluded) {
		this.isHolidayIncluded = isHolidayIncluded;
	}

	public final String getDanceGrade() {
		return danceGrade;
	}

	public final void setDanceGrade(String danceGrade) {
		this.danceGrade = danceGrade;
	}

	public final Integer getAuditorId() {
		return auditorId;
	}

	public final void setAuditorId(Integer auditorId) {
		this.auditorId = auditorId;
	}

	public final String getConnotation() {
		return connotation;
	}

	public final void setConnotation(String connotation) {
		this.connotation = connotation;
	}

	public final String getHelpInformation() {
		return helpInformation;
	}

	public final void setHelpInformation(String helpInformation) {
		this.helpInformation = helpInformation;
	}

	public final String getBriefIntroduction() {
		return briefIntroduction;
	}

	public final void setBriefIntroduction(String briefIntroduction) {
		this.briefIntroduction = briefIntroduction;
	}

	public final String getPictureNo() {
		return pictureNo;
	}

	public final void setPictureNo(String pictureNo) {
		this.pictureNo = pictureNo;
	}

	public final String getVideoUrl() {
		return videoUrl;
	}

	public final void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public final String getAudioName() {
		return audioName;
	}

	public final void setAudioName(String audioName) {
		this.audioName = audioName;
	}

	public final String getAudioUrl() {
		return audioUrl;
	}

	public final void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	public final String getDanceIntroduction() {
		return danceIntroduction;
	}

	public final void setDanceIntroduction(String danceIntroduction) {
		this.danceIntroduction = danceIntroduction;
	}

	public String getMonIntervalId() {
		return monIntervalId;
	}

	public String getMonRoomId() {
		return monRoomId;
	}

	public String getTueIntervalId() {
		return tueIntervalId;
	}

	public String getTueRoomId() {
		return tueRoomId;
	}

	public void setMonIntervalId(String monIntervalId) {
		this.monIntervalId = monIntervalId;
	}

	public void setMonRoomId(String monRoomId) {
		this.monRoomId = monRoomId;
	}

	public void setTueIntervalId(String tueIntervalId) {
		this.tueIntervalId = tueIntervalId;
	}

	public void setTueRoomId(String tueRoomId) {
		this.tueRoomId = tueRoomId;
	}
	

	
}
