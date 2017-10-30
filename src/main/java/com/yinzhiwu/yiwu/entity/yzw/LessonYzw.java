package com.yinzhiwu.yiwu.entity.yzw;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.yinzhiwu.yiwu.entity.LessonComment;
import com.yinzhiwu.yiwu.entity.LessonPraise;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseTypeConverter;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.SubCourseType;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.SubCourseTypeConverter;

@SuppressWarnings("serial")
@Entity
@Table(name = "vlesson")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class LessonYzw extends BaseYzwEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "course_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private CourseYzw course;

	private Integer ordinalNo;
	private Date lessonDate;

	@Column
	private Time startTime;

	@Column
	private Time endTime;

	@Column(name = "courseDesc")
	private String name;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "storeId", foreignKey = @ForeignKey(name = "fk_lesson_store_id", value = ConstraintMode.NO_CONSTRAINT))
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private DepartmentYzw store;

	@Column(length = 32)
	private String storeName;

	@Column
	private Float lessonTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "yindaoTeacherId", foreignKey = @ForeignKey(name = "fk_lesson_dueTeacher_id", value = ConstraintMode.NO_CONSTRAINT))
	private EmployeeYzw dueTeacher;

	@Column(length = 32, name = "yindaoTeacherName")
	private String dueTeacherName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "classRoomId", foreignKey = @ForeignKey(name = "fk_lesson_classRoom_id", value = ConstraintMode.NO_CONSTRAINT))
	private ClassRoomYzw classRoom;

	@Column(length = 32)
	private String classRoomName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shidaoTeacherId", foreignKey = @ForeignKey(name = "fk_lesson_actualTeacher_id", value = ConstraintMode.NO_CONSTRAINT))
	private EmployeeYzw actualTeacher;

	@Column(name = "shidaoTeacherName", length = 32)
	private String actualTeacherName;

	@Column(length = 32)
	@Convert(converter=LessonStatusConverter.class)
	private LessonStatus lessonStatus;

	@Column(length = 32)
	@Convert(converter=CourseTypeConverter.class)
	private CourseType courseType;

	@Column(length = 64)
	@Convert(converter=SubCourseTypeConverter.class)
	private SubCourseType subCourseType;

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

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "connotation", column = @Column(name = "neihan")),
			@AttributeOverride(name = "helpInfomation", column = @Column(name = "help")),
			@AttributeOverride(name = "introduction", column = @Column(name = "jianjie")),
			@AttributeOverride(name = "pictureUri", column = @Column(name = "picture")),
			@AttributeOverride(name = "puzzleVideoUri", column = @Column(name = "puzzleVideoUri")),
			@AttributeOverride(name = "practicalVideoUri", column = @Column(name = "practicalVideoUri")),
			@AttributeOverride(name = "audioName", column = @Column(name = "audio")),
			@AttributeOverride(name = "audioUri", column = @Column(name = "audio_link")),
			@AttributeOverride(name = "danceIntroduction", column = @Column(name = "dance_introduction")) })
	private LessonConnotation connotation;

	@Column(name = "QRcode")
	private String qrCode;

	@OneToMany(mappedBy="lesson")
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private List<LessonPraise> praises = new ArrayList<>();
	
	@OneToMany(mappedBy="lesson")
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private List<LessonComment> comments = new ArrayList<>();
	
	public LessonYzw() {
		super();
	}

	public Integer getId() {
		return id;
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

	public EmployeeYzw getActualTeacher() {
		return actualTeacher;
	}

	public String getActualTeacherName() {
		return actualTeacherName;
	}

	public LessonStatus getLessonStatus() {
		return lessonStatus;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public SubCourseType getSubCourseType() {
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

	public String getQrCode() {
		return qrCode;
	}

	public void setId(Integer id) {
		this.id = id;
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
		this.actualTeacher = actualTeacherId;
	}

	public void setActualTeacherName(String actualTeacherName) {
		this.actualTeacherName = actualTeacherName;
	}

	public void setLessonStatus(LessonStatus lessonStatus) {
		this.lessonStatus = lessonStatus;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public void setSubCourseType(SubCourseType subCourseType) {
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

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public ClassRoomYzw getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoomYzw classRoom) {
		this.classRoom = classRoom;
	}

	public CourseYzw getCourse() {
		return course;
	}

	public void setCourse(CourseYzw course) {
		this.course = course;
	}

	public LessonConnotation getConnotation() {
		return connotation;
	}

	public void setConnotation(LessonConnotation connotation) {
		this.connotation = connotation;
	}

	
	
	public enum LessonStatus{
		UN_CHECKED("未审核"),
		ARRANGED("已排课"),
		FINISHED("已开课");
		
		private final String status;

		public String getStatus() {
			return status;
		}
		
		private LessonStatus(String status){
			this.status = status;
		}
		
		public static LessonStatus fromStatus(String status){
			switch (status) {
			case "未审核":
				return LessonStatus.UN_CHECKED;
			case "已排课":
				return LessonStatus.ARRANGED;
			case "已开课":
				return LessonStatus.FINISHED;
			default:
				throw new UnsupportedOperationException(status + " not supported for enum LessonStatus");
			}
		}
	}
	
	@Converter
	public static class LessonStatusConverter implements AttributeConverter<LessonStatus, String>{

		@Override
		public String convertToDatabaseColumn(LessonStatus attribute) {
			if(attribute ==null)
				return null;
			return attribute.getStatus();
		}

		@Override
		public LessonStatus convertToEntityAttribute(String dbData) {
			if(dbData == null || "".equals(dbData.trim()))
				return null;
			return LessonStatus.fromStatus(dbData);
		}
		
	}

	public Integer getOrdinalNo() {
		return ordinalNo;
	}

	public void setOrdinalNo(Integer ordinalNo) {
		this.ordinalNo = ordinalNo;
	}

	public void setActualTeacher(EmployeeYzw actualTeacher) {
		this.actualTeacher = actualTeacher;
	}

	public List<LessonPraise> getPraises() {
		return praises;
	}

	public void setPraises(List<LessonPraise> praises) {
		this.praises = praises;
	}

	public List<LessonComment> getComments() {
		return comments;
	}

	public void setComments(List<LessonComment> comments) {
		this.comments = comments;
	}

	
}
