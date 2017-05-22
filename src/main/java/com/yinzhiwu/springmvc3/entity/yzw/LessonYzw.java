package com.yinzhiwu.springmvc3.entity.yzw;

import java.sql.Time;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vlesson")
public class LessonYzw extends BaseYzwEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 32)
	private String courseid;

	@Column
	private Date lessonDate;

	@Column
	private Time startTime;

	@Column
	private Time endTime;

	@Column(name = "courseDesc")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="storeId", foreignKey=
			@ForeignKey(name="fk_lesson_store_id", value=ConstraintMode.NO_CONSTRAINT))
	private DepartmentYzw store;

	@Column(length = 32)
	private String storeName;

	@Column
	private Float lessonTime;

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "yindaoTeacherId", foreignKey=
			@ForeignKey(name="fk_lesson_dueTeacher_id", value=ConstraintMode.NO_CONSTRAINT))
	private EmployeeYzw dueTeacher;

	@Column(length = 32, name = "yindaoTeacherName")
	private String dueTeacherName;

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="classRoomId", foreignKey=
			@ForeignKey(name="fk_lesson_classRoom_id", value=ConstraintMode.NO_CONSTRAINT))
	private ClassRoomYzw classRoom;

	@Column(length = 32)
	private String classRoomName;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "shidaoTeacherId", foreignKey=
			@ForeignKey(name="fk_lesson_actualTeacher_id" ,value=ConstraintMode.NO_CONSTRAINT))
	private EmployeeYzw actualTeacherId;

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
	
	public LessonYzw(){
		super();
	}

	public Integer getId() {
		return id;
	}

	public String getCourseid() {
		return courseid;
	}

	public Date getLessonDate() {
		return lessonDate;
	}

	public Time getStartTime() {
		return startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public String getName() {
		return name;
	}

	public DepartmentYzw getStore() {
		return store;
	}

	public String getStoreName() {
		return storeName;
	}

	public Float getLessonTime() {
		return lessonTime;
	}

	public EmployeeYzw getDueTeacher() {
		return dueTeacher;
	}

	public String getDueTeacherName() {
		return dueTeacherName;
	}


	public String getClassRoomName() {
		return classRoomName;
	}

	public EmployeeYzw getActualTeacherId() {
		return actualTeacherId;
	}

	public String getActualTeacherName() {
		return actualTeacherName;
	}

	public String getLessonStatus() {
		return lessonStatus;
	}

	public String getCourseType() {
		return courseType;
	}

	public String getSubCourseType() {
		return subCourseType;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public Integer getLessonMinutes() {
		return lessonMinutes;
	}

	public String getAppointedContract() {
		return appointedContract;
	}

	public Integer getDueStudentCount() {
		return dueStudentCount;
	}

	public Integer getExperienceStudentCount() {
		return experienceStudentCount;
	}

	public Integer getRollCalledStudentCount() {
		return rollCalledStudentCount;
	}

	public Integer getActualStudentCount() {
		return actualStudentCount;
	}

	public Integer getCheckedInStudentCount() {
		return checkedInStudentCount;
	}

	public Integer getAppointedStudentCount() {
		return appointedStudentCount;
	}

	public String getConnotation() {
		return connotation;
	}

	public String getHelpInfomation() {
		return helpInfomation;
	}

	public String getIntroduction() {
		return introduction;
	}

	public String getPictureNo() {
		return pictureNo;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public String getAudioName() {
		return audioName;
	}

	public String getAudioUrl() {
		return audioUrl;
	}

	public String getDanceIntroduction() {
		return danceIntroduction;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public void setLessonDate(Date lessonDate) {
		this.lessonDate = lessonDate;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStore(DepartmentYzw store) {
		this.store = store;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setLessonTime(Float lessonTime) {
		this.lessonTime = lessonTime;
	}

	public void setDueTeacher(EmployeeYzw dueTeacher) {
		this.dueTeacher = dueTeacher;
	}

	public void setDueTeacherName(String dueTeacherName) {
		this.dueTeacherName = dueTeacherName;
	}

	public void setClassRoomName(String classRoomName) {
		this.classRoomName = classRoomName;
	}

	public void setActualTeacherId(EmployeeYzw actualTeacherId) {
		this.actualTeacherId = actualTeacherId;
	}

	public void setActualTeacherName(String actualTeacherName) {
		this.actualTeacherName = actualTeacherName;
	}

	public void setLessonStatus(String lessonStatus) {
		this.lessonStatus = lessonStatus;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public void setSubCourseType(String subCourseType) {
		this.subCourseType = subCourseType;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public void setLessonMinutes(Integer lessonMinutes) {
		this.lessonMinutes = lessonMinutes;
	}

	public void setAppointedContract(String appointedContract) {
		this.appointedContract = appointedContract;
	}

	public void setDueStudentCount(Integer dueStudentCount) {
		this.dueStudentCount = dueStudentCount;
	}

	public void setExperienceStudentCount(Integer experienceStudentCount) {
		this.experienceStudentCount = experienceStudentCount;
	}

	public void setRollCalledStudentCount(Integer rollCalledStudentCount) {
		this.rollCalledStudentCount = rollCalledStudentCount;
	}

	public void setActualStudentCount(Integer actualStudentCount) {
		this.actualStudentCount = actualStudentCount;
	}

	public void setCheckedInStudentCount(Integer checkedInStudentCount) {
		this.checkedInStudentCount = checkedInStudentCount;
	}

	public void setAppointedStudentCount(Integer appointedStudentCount) {
		this.appointedStudentCount = appointedStudentCount;
	}

	public void setConnotation(String connotation) {
		this.connotation = connotation;
	}

	public void setHelpInfomation(String helpInfomation) {
		this.helpInfomation = helpInfomation;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public void setPictureNo(String pictureNo) {
		this.pictureNo = pictureNo;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public void setAudioName(String audioName) {
		this.audioName = audioName;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	public void setDanceIntroduction(String danceIntroduction) {
		this.danceIntroduction = danceIntroduction;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public ClassRoomYzw getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoomYzw classRoom) {
		this.classRoom = classRoom;
	}

	

}
