package com.yinzhiwu.yiwu.entity.yzwOld;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vlesson")
public class Lesson {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer lessonId;

	@Column(length = 32)
	private String courseid;

	@Column
	private Date lessonDate;

	@Column
	private Time startTime;

	@Column
	private Time endTime;

	@Column(name = "courseDesc")
	private String lessonDesc;

	@Column
	private Integer storeId;

	@Column(length = 32)
	private String storeName;

	@Column
	private Float lessonTime;

	@Column(name = "yindaoTeacherId")
	private Integer dueTeacherId;

	@Column(length = 32, name = "yindaoTeacherName")
	private String dueTeacherName;

	@Column(length = 32)
	private String classRoomId;

	@Column(length = 32)
	private String classRoomName;

	@Column(name = "shidaoTeacherId")
	private Integer actualTeacherId;

	@Column(name = "shidaoTeacherName", length = 32)
	private String actualTeacherName;

	@Column(length = 32)
	private String lessonStatus;

	@Column(length = 32)
	private String courseType;

	@Column(length = 64)
	private String subCourseType;

	@Column(name = "flag_delete")
	private Integer isDelete;

	@Column(name = "start_date_time")
	private Date startDateTime;

	@Column(name = "lessonTime_minutes")
	private Integer lessonMinutes;

	@Column(name = "appointed_huijiheyue")
	private String appointedContract;

	@Column(name = "yindaoRenshu")
	private Integer dueStudentCount;

	@Column(name = "tiyanRenshu")
	private Integer experienceStudentCount;

	@Column(name = "dianmingRenshu")
	private Integer rollCalledStudentCount;

	@Column(name = "shidaoRenshu")
	private Integer actualStudentCount;

	@Column(name = "qiandaoRenshu")
	private Integer checkedInStudentCount;

	@Column(name = "yuyueRenshu")
	private Integer appointedStudentCount;

	@Column(name = "neihan")
	private String connotation;

	@Column(name = "help")
	private String helpInfomation;

	@Column(name = "jianjie")
	private String introduction;

	@Column(name = "picture")
	private String pictureNo;

	@Column(name = "video")
	private String videoUrl;

	@Column(name = "audio")
	private String audioName;

	@Column(name = "audio_link")
	private String audioUrl;

	@Column(name = "dance_introduction")
	private String danceIntroduction;

	@Column(name = "QRcode")
	private String qrCode;

	@Column(name = "sf_create_user")
	private Integer createUserId;

	@Column(name = "sf_create_time")
	private Date createTime;

	@Column(name = "sf_last_change_user")
	private Integer lastChangeUserId;

	@Column(name = "sf_last_change_time")
	private Date lastChangeTime;

	@Column(name = "machineCode")
	private Integer machineCode;

	@Column(name = "sf_last_sync_timeStamp")
	private Date lastSyncTimeStamp;

	@Column(name = "sf_last_change_timeStamp")
	private Date lastChangeTimeStamp;

	public Lesson(Integer lessonId, String courseid, Date lessonDate, Time startTime, Time endTime, String lessonDesc,
			Integer storeId, String storeName, Float lessonTime, Integer dueTeacherId, String dueTeacherName,
			String courseType, String subCourseType) {
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
	}

	public Lesson() {
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

	public final String getClassRoomName() {
		return classRoomName;
	}

	public final void setClassRoomName(String classRoomName) {
		this.classRoomName = classRoomName;
	}

	public final Integer getActualTeacherId() {
		return actualTeacherId;
	}

	public final void setActualTeacherId(Integer actualTeacherId) {
		this.actualTeacherId = actualTeacherId;
	}

	public final String getActualTeacherName() {
		return actualTeacherName;
	}

	public final void setActualTeacherName(String actualTeacherName) {
		this.actualTeacherName = actualTeacherName;
	}

	public final String getLessonStatus() {
		return lessonStatus;
	}

	public final void setLessonStatus(String lessonStatus) {
		this.lessonStatus = lessonStatus;
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

	public final Integer getIsDelete() {
		return isDelete;
	}

	public final void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public final Date getStartDateTime() {
		return startDateTime;
	}

	public final void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public final Integer getLessonMinutes() {
		return lessonMinutes;
	}

	public final void setLessonMinutes(Integer lessonMinutes) {
		this.lessonMinutes = lessonMinutes;
	}

	public final String getAppointedContract() {
		return appointedContract;
	}

	public final void setAppointedContract(String appointedContract) {
		this.appointedContract = appointedContract;
	}

	public final Integer getDueStudentCount() {
		return dueStudentCount;
	}

	public final void setDueStudentCount(Integer dueStudentCount) {
		this.dueStudentCount = dueStudentCount;
	}

	public final Integer getExperienceStudentCount() {
		return experienceStudentCount;
	}

	public final void setExperienceStudentCount(Integer experienceStudentCount) {
		this.experienceStudentCount = experienceStudentCount;
	}

	public final Integer getRollCalledStudentCount() {
		return rollCalledStudentCount;
	}

	public final void setRollCalledStudentCount(Integer rollCalledStudentCount) {
		this.rollCalledStudentCount = rollCalledStudentCount;
	}

	public final Integer getActualStudentCount() {
		return actualStudentCount;
	}

	public final void setActualStudentCount(Integer actualStudentCount) {
		this.actualStudentCount = actualStudentCount;
	}

	public final Integer getCheckedInStudentCount() {
		return checkedInStudentCount;
	}

	public final void setCheckedInStudentCount(Integer checkedInStudentCount) {
		this.checkedInStudentCount = checkedInStudentCount;
	}

	public final Integer getAppointedStudentCount() {
		return appointedStudentCount;
	}

	public final void setAppointedStudentCount(Integer appointedStudentCount) {
		this.appointedStudentCount = appointedStudentCount;
	}

	public final String getConnotation() {
		return connotation;
	}

	public final void setConnotation(String connotation) {
		this.connotation = connotation;
	}

	public final String getHelpInfomation() {
		return helpInfomation;
	}

	public final void setHelpInfomation(String helpInfomation) {
		this.helpInfomation = helpInfomation;
	}

	public final String getIntroduction() {
		return introduction;
	}

	public final void setIntroduction(String introduction) {
		this.introduction = introduction;
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

	public final String getQrCode() {
		return qrCode;
	}

	public final void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public final Integer getCreateUserId() {
		return createUserId;
	}

	public final void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public final Date getCreateTime() {
		return createTime;
	}

	public final void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public final Integer getLastChangeUserId() {
		return lastChangeUserId;
	}

	public final void setLastChangeUserId(Integer lastChangeUserId) {
		this.lastChangeUserId = lastChangeUserId;
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

	public final Date getLastChangeTimeStamp() {
		return lastChangeTimeStamp;
	}

	public final void setLastChangeTimeStamp(Date lastChangeTimeStamp) {
		this.lastChangeTimeStamp = lastChangeTimeStamp;
	}

	public final String getClassRoomId() {
		return classRoomId;
	}

	public final void setClassRoomId(String classRoomId) {
		this.classRoomId = classRoomId;
	}

}
