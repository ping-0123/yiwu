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
@Table(name="vcourse")
public class CourseYzw extends BaseYzwEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7726855810795537857L;

	@Id
	@Column(length=32)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	
	@Column(length=128)
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="dance_id", foreignKey=
			@ForeignKey(name="fk_course_dance_id", value=ConstraintMode.NO_CONSTRAINT))
	private DanceYzw dance;
	

	@Column(length =32)
	private String danceDesc;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="store_id", foreignKey=
			@ForeignKey(name="fk_course_store_id", value=ConstraintMode.NO_CONSTRAINT))
	private DepartmentYzw store;
	
	@Column(length=32)
	private String storeName;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="teacher_id", foreignKey=
			@ForeignKey(name="fk_course_teacher_id", value=ConstraintMode.NO_CONSTRAINT))
	private EmployeeYzw teacher;
	
	@Column(length=32)
	private String teacherName;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="interval_id",foreignKey=
			@ForeignKey(name="fk_coruse_interval_id", value=ConstraintMode.NO_CONSTRAINT))
	private IntervalYzw interval;
	
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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="classRoom_id", foreignKey=
			@ForeignKey(name="fk_courser_classRoom_id", value=ConstraintMode.NO_CONSTRAINT))
	private ClassRoomYzw classRoom;
	
	@Column(length=32)
	private String classRoomName;
	
	@Column
	private Float weeklyCourseHours;
	
	@Column(name="delete_flag")
	private Integer isDeleted;
	
	@Column(name="weeks", length=32)
	private String weekes;
	
	private String courseType;
	
	@Column(length=32)
	private String subCourseType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="monInterval_id", foreignKey=
			@ForeignKey(name="fk_course_monInterval_id", value=ConstraintMode.NO_CONSTRAINT))
	private IntervalYzw monInterval;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="monRoom_id", foreignKey=
			@ForeignKey(name="fk_course_monRoom_id", value=ConstraintMode.NO_CONSTRAINT))
	private ClassRoomYzw monRoomId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tueInterval_id", foreignKey=
			@ForeignKey(name="fk_course_tueInterval_id", value=ConstraintMode.NO_CONSTRAINT))
	private IntervalYzw tueInterval;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tueRoom_id", foreignKey=
			@ForeignKey(name="fk_course_tueRoom_id", value=ConstraintMode.NO_CONSTRAINT))
	private ClassRoomYzw tueRoom;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="wedInterval_id", foreignKey=
			@ForeignKey(name="fk_course_wedInterval_id", value=ConstraintMode.NO_CONSTRAINT))
	private IntervalYzw wedInterval;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="wedRoom_id", foreignKey=
			@ForeignKey(name="fk_course_wedRoom_id", value=ConstraintMode.NO_CONSTRAINT))
	private ClassRoomYzw wedRoom;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="thuInterval_id", foreignKey=
			@ForeignKey(name="fk_course_thuInterval_id", value=ConstraintMode.NO_CONSTRAINT))
	private IntervalYzw thuInterval;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="thuRoom_id", foreignKey=
			@ForeignKey(name="fk_course_thuRoom_id", value=ConstraintMode.NO_CONSTRAINT))
	private ClassRoomYzw thuRoom;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="friInterval_id", foreignKey=
			@ForeignKey(name="fk_course_friInterval_id", value=ConstraintMode.NO_CONSTRAINT))
	private IntervalYzw friInterval;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="friRoom_id", foreignKey=
			@ForeignKey(name="fk_course_friRoom_id", value=ConstraintMode.NO_CONSTRAINT))
	private ClassRoomYzw friRoom;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="satInterval_id", foreignKey=
			@ForeignKey(name="fk_course_satInterval_id", value=ConstraintMode.NO_CONSTRAINT))
	private IntervalYzw satInterval;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="satRoom_id", foreignKey=
			@ForeignKey(name="fk_course_satRoom_id", value=ConstraintMode.NO_CONSTRAINT))
	private ClassRoomYzw satRoom;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sunInterval_id", foreignKey=
			@ForeignKey(name="fk_course_sunInterval_id", value=ConstraintMode.NO_CONSTRAINT))
	private IntervalYzw sunInterval;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sunRoom_id", foreignKey=
			@ForeignKey(name="fk_course_sunRoom_id", value=ConstraintMode.NO_CONSTRAINT))
	private ClassRoomYzw sunRoom;
	
	@Column(length=32, name="status")
	private String courseStatus;
	
	@Column
	private Integer studentCount;
	
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
	
	
//	@OneToMany
//	List<OrderYzw> orders = new ArrayList<>();

	

	public CourseYzw() {
		super();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public DanceYzw getDance() {
		return dance;
	}

	public String getDanceDesc() {
		return danceDesc;
	}

	public DepartmentYzw getStore() {
		return store;
	}

	public String getStoreName() {
		return storeName;
	}

	public EmployeeYzw getTeacher() {
		return teacher;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public IntervalYzw getInterval() {
		return interval;
	}

	public String getIntervalDesc() {
		return intervalDesc;
	}

	public Time getStartTime() {
		return startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Float getSumCourseHours() {
		return sumCourseHours;
	}

	public ClassRoomYzw getClassRoom() {
		return classRoom;
	}

	public String getClassRoomName() {
		return classRoomName;
	}

	public Float getWeeklyCourseHours() {
		return weeklyCourseHours;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public String getWeekes() {
		return weekes;
	}

	public String getCourseType() {
		return courseType;
	}

	public String getSubCourseType() {
		return subCourseType;
	}

	public IntervalYzw getMonInterval() {
		return monInterval;
	}

	public ClassRoomYzw getMonRoomId() {
		return monRoomId;
	}

	public IntervalYzw getTueInterval() {
		return tueInterval;
	}

	public ClassRoomYzw getTueRoom() {
		return tueRoom;
	}

	public IntervalYzw getWedInterval() {
		return wedInterval;
	}

	public ClassRoomYzw getWedRoom() {
		return wedRoom;
	}

	public IntervalYzw getThuInterval() {
		return thuInterval;
	}

	public ClassRoomYzw getThuRoom() {
		return thuRoom;
	}

	public IntervalYzw getFriInterval() {
		return friInterval;
	}

	public ClassRoomYzw getFriRoom() {
		return friRoom;
	}

	public IntervalYzw getSatInterval() {
		return satInterval;
	}

	public ClassRoomYzw getSatRoom() {
		return satRoom;
	}

	public IntervalYzw getSunInterval() {
		return sunInterval;
	}

	public ClassRoomYzw getSunRoom() {
		return sunRoom;
	}

	public String getCourseStatus() {
		return courseStatus;
	}

	public Integer getStudentCount() {
		return studentCount;
	}

	public Integer getIsHolidayIncluded() {
		return isHolidayIncluded;
	}

	public String getDanceGrade() {
		return danceGrade;
	}

	public Integer getAuditorId() {
		return auditorId;
	}

	public String getConnotation() {
		return connotation;
	}

	public String getHelpInformation() {
		return helpInformation;
	}

	public String getBriefIntroduction() {
		return briefIntroduction;
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

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDance(DanceYzw dance) {
		this.dance = dance;
	}

	public void setDanceDesc(String danceDesc) {
		this.danceDesc = danceDesc;
	}

	public void setStore(DepartmentYzw store) {
		this.store = store;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setTeacher(EmployeeYzw teacher) {
		this.teacher = teacher;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public void setInterval(IntervalYzw interval) {
		this.interval = interval;
	}

	public void setIntervalDesc(String intervalDesc) {
		this.intervalDesc = intervalDesc;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setSumCourseHours(Float sumCourseHours) {
		this.sumCourseHours = sumCourseHours;
	}

	public void setClassRoom(ClassRoomYzw classRoom) {
		this.classRoom = classRoom;
	}

	public void setClassRoomName(String classRoomName) {
		this.classRoomName = classRoomName;
	}

	public void setWeeklyCourseHours(Float weeklyCourseHours) {
		this.weeklyCourseHours = weeklyCourseHours;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setWeekes(String weekes) {
		this.weekes = weekes;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public void setSubCourseType(String subCourseType) {
		this.subCourseType = subCourseType;
	}

	public void setMonInterval(IntervalYzw monInterval) {
		this.monInterval = monInterval;
	}

	public void setMonRoomId(ClassRoomYzw monRoomId) {
		this.monRoomId = monRoomId;
	}

	public void setTueInterval(IntervalYzw tueInterval) {
		this.tueInterval = tueInterval;
	}

	public void setTueRoom(ClassRoomYzw tueRoom) {
		this.tueRoom = tueRoom;
	}

	public void setWedInterval(IntervalYzw wedInterval) {
		this.wedInterval = wedInterval;
	}

	public void setWedRoom(ClassRoomYzw wedRoom) {
		this.wedRoom = wedRoom;
	}

	public void setThuInterval(IntervalYzw thuInterval) {
		this.thuInterval = thuInterval;
	}

	public void setThuRoom(ClassRoomYzw thuRoom) {
		this.thuRoom = thuRoom;
	}

	public void setFriInterval(IntervalYzw friInterval) {
		this.friInterval = friInterval;
	}

	public void setFriRoom(ClassRoomYzw friRoom) {
		this.friRoom = friRoom;
	}

	public void setSatInterval(IntervalYzw satInterval) {
		this.satInterval = satInterval;
	}

	public void setSatRoom(ClassRoomYzw satRoom) {
		this.satRoom = satRoom;
	}

	public void setSunInterval(IntervalYzw sunInterval) {
		this.sunInterval = sunInterval;
	}

	public void setSunRoom(ClassRoomYzw sunRoom) {
		this.sunRoom = sunRoom;
	}

	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}

	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}

	public void setIsHolidayIncluded(Integer isHolidayIncluded) {
		this.isHolidayIncluded = isHolidayIncluded;
	}

	public void setDanceGrade(String danceGrade) {
		this.danceGrade = danceGrade;
	}

	public void setAuditorId(Integer auditorId) {
		this.auditorId = auditorId;
	}

	public void setConnotation(String connotation) {
		this.connotation = connotation;
	}

	public void setHelpInformation(String helpInformation) {
		this.helpInformation = helpInformation;
	}

	public void setBriefIntroduction(String briefIntroduction) {
		this.briefIntroduction = briefIntroduction;
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


	
	
}
