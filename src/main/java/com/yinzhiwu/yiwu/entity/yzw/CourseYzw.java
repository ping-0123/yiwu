package com.yinzhiwu.yiwu.entity.yzw;

import java.sql.Time;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
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
import javax.persistence.Table;

import org.springframework.util.StringUtils;

@Entity
@Table(name = "vcourse")
public class CourseYzw extends BaseYzwEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7726855810795537857L;
	
	public enum CourseType{
		CLOSED("封闭式"),
		OPENED("开放式"),
		PRIVATE("私教课"),
		GRADE_EXAM("考级"),
		REFERENCE_ORDER("已订单为准");
		
		
		private final String name;
		public String getName(){
			return name;
		}
		private CourseType(String name){
			this.name = name;
		}
		
		public static CourseType fromName(String name){
			switch (name) {
				case "封闭式":
					return CourseType.CLOSED;
				case "开放式":
				return CourseType.OPENED;
				case "私教课":
					return CourseType.PRIVATE;
				case "考级":
					return CourseType.GRADE_EXAM;
				case "已订单为准":
					return CourseType.REFERENCE_ORDER;
				default:
					throw new UnsupportedOperationException(name + "is not supported for enum CourseType");
			}
		}
	}
	
	@Converter
	public static class CourseTypeConverter implements AttributeConverter<CourseType, String>{

		@Override
		public String convertToDatabaseColumn(CourseType attribute) {
			if(attribute == null 
					|| attribute == CourseType.GRADE_EXAM 
					|| attribute == CourseType.REFERENCE_ORDER)
				return null;
			return attribute.getName();
		}

		@Override
		public CourseType convertToEntityAttribute(String dbData) {
			if(dbData == null)
				return null;
			return CourseType.fromName(dbData);
		}
		
	}
	
	
	public enum SubCourseType {
		
		OPEN_A(1,"开放式A", CourseType.OPENED),
		OPEN_B(2,"开放式B", CourseType.OPENED),
		CLOSED(3,"封闭式", CourseType.CLOSED),
		PRIVATE(4,"私教课", CourseType.PRIVATE);
		
		private final int id;
		private final String name;
		private final CourseType supperType;
		
		private SubCourseType(int id,String name ,CourseType type){
			this.id = id;
			this.name= name;
			this.supperType= type;
		}

		public Integer getId() {
			return id;
		}
		public String getName() {
			return name;
		}

		public CourseType getSupperType() {
			return supperType;
		}
		
		public static SubCourseType fromId(Integer id){
			switch (id) {
			case 1:
				return SubCourseType.OPEN_A;
			case 2:
				return SubCourseType.OPEN_B;
			case 3:
				return SubCourseType.CLOSED;
			case 4:
				return SubCourseType.PRIVATE;
			default:
				throw new UnsupportedOperationException(id + "is not supported from enum SubCourseType");
			}
		}
		
		public static SubCourseType fromName(String name){
			switch (name) {
			case "封闭式":
				return SubCourseType.CLOSED;
			case "开放式A":
				return SubCourseType.OPEN_A;
			case "开放式B":
				return SubCourseType.OPEN_B;
			case "私教课":
				return SubCourseType.PRIVATE;
			default:
				throw new UnsupportedOperationException(
						name + "is not supported from enum SubCourseType");
			}
		}

	}

	@Converter
	public static class SubCourseTypeConverter implements AttributeConverter<SubCourseType, String>{

		@Override
		public String convertToDatabaseColumn(SubCourseType attribute) {
			if(attribute == null)
				return null;
			return attribute.getName();
		}

		@Override
		public SubCourseType convertToEntityAttribute(String dbData) {
			if(!StringUtils.hasLength(dbData))
				return null;
			return SubCourseType.fromName(dbData);
		}
		
	}
	
	@Converter
	public static class SubCourseTypeIntegerConverter implements AttributeConverter<SubCourseType, Integer>{

		@Override
		public Integer convertToDatabaseColumn(SubCourseType attribute) {
			if(attribute == null)
				return null;
			return attribute.getId();
		}

		@Override
		public SubCourseType convertToEntityAttribute(Integer dbData) {
			if(dbData == null)
				return null;
			return SubCourseType.fromId(dbData);
		}
		
	}
	
	@Id
	@Column(length = 32)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(length = 128)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "dance_id", foreignKey = @ForeignKey(name = "fk_course_dance_id", value = ConstraintMode.NO_CONSTRAINT))
	private DanceYzw dance;

	@Column(length = 32)
	private String danceDesc;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "store_id", foreignKey = @ForeignKey(name = "fk_course_store_id", value = ConstraintMode.NO_CONSTRAINT))
	private DepartmentYzw store;

	@Column(length = 32)
	private String storeName;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "teacher_id", foreignKey = @ForeignKey(name = "fk_course_teacher_id", value = ConstraintMode.NO_CONSTRAINT))
	private EmployeeYzw teacher;

	@Column(length = 32)
	private String teacherName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "interval_id", foreignKey = @ForeignKey(name = "fk_coruse_interval_id", value = ConstraintMode.NO_CONSTRAINT))
	private IntervalYzw interval;

	@Column(length = 32)
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "classRoom_id", foreignKey = @ForeignKey(name = "fk_courser_classRoom_id", value = ConstraintMode.NO_CONSTRAINT))
	private ClassRoomYzw classRoom;

	@Column(length = 32)
	private String classRoomName;

	@Column
	private Float weeklyCourseHours;

	@Column(name = "delete_flag")
	private Integer isDeleted;

	@Column(name = "weeks", length = 32)
	private String weekes;

	@Convert(converter=CourseTypeConverter.class)
	private CourseType courseType;

	@Convert(converter=SubCourseTypeConverter.class)
	@Column(length =32)
	private SubCourseType subCourseType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "monInterval_id", foreignKey = @ForeignKey(name = "fk_course_monInterval_id", value = ConstraintMode.NO_CONSTRAINT))
	private IntervalYzw monInterval;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "monRoom_id", foreignKey = @ForeignKey(name = "fk_course_monRoom_id", value = ConstraintMode.NO_CONSTRAINT))
	private ClassRoomYzw monRoomId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tueInterval_id", foreignKey = @ForeignKey(name = "fk_course_tueInterval_id", value = ConstraintMode.NO_CONSTRAINT))
	private IntervalYzw tueInterval;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tueRoom_id", foreignKey = @ForeignKey(name = "fk_course_tueRoom_id", value = ConstraintMode.NO_CONSTRAINT))
	private ClassRoomYzw tueRoom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wedInterval_id", foreignKey = @ForeignKey(name = "fk_course_wedInterval_id", value = ConstraintMode.NO_CONSTRAINT))
	private IntervalYzw wedInterval;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wedRoom_id", foreignKey = @ForeignKey(name = "fk_course_wedRoom_id", value = ConstraintMode.NO_CONSTRAINT))
	private ClassRoomYzw wedRoom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "thuInterval_id", foreignKey = @ForeignKey(name = "fk_course_thuInterval_id", value = ConstraintMode.NO_CONSTRAINT))
	private IntervalYzw thuInterval;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "thuRoom_id", foreignKey = @ForeignKey(name = "fk_course_thuRoom_id", value = ConstraintMode.NO_CONSTRAINT))
	private ClassRoomYzw thuRoom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "friInterval_id", foreignKey = @ForeignKey(name = "fk_course_friInterval_id", value = ConstraintMode.NO_CONSTRAINT))
	private IntervalYzw friInterval;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "friRoom_id", foreignKey = @ForeignKey(name = "fk_course_friRoom_id", value = ConstraintMode.NO_CONSTRAINT))
	private ClassRoomYzw friRoom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "satInterval_id", foreignKey = @ForeignKey(name = "fk_course_satInterval_id", value = ConstraintMode.NO_CONSTRAINT))
	private IntervalYzw satInterval;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "satRoom_id", foreignKey = @ForeignKey(name = "fk_course_satRoom_id", value = ConstraintMode.NO_CONSTRAINT))
	private ClassRoomYzw satRoom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sunInterval_id", foreignKey = @ForeignKey(name = "fk_course_sunInterval_id", value = ConstraintMode.NO_CONSTRAINT))
	private IntervalYzw sunInterval;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sunRoom_id", foreignKey = @ForeignKey(name = "fk_course_sunRoom_id", value = ConstraintMode.NO_CONSTRAINT))
	private ClassRoomYzw sunRoom;

	@Column(length = 32, name = "status")
	private String courseStatus;

	@Column
	private Integer studentCount;

	@Column(name = "include_holeday_flag")
	private Integer isHolidayIncluded;

	@Column(length = 32)
	private String danceGrade;

	@Column(name = "ShenheRen")
	private Integer auditorId;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "connotation", column = @Column(name = "connotation")),
			@AttributeOverride(name = "helpInfomation", column = @Column(name = "help")),
			@AttributeOverride(name = "introduction", column = @Column(name = "briefIntroduction")),
			@AttributeOverride(name = "pictureNo", column = @Column(name = "picture")),
			@AttributeOverride(name = "videoUrl", column = @Column(name = "videoUrl")),
			@AttributeOverride(name = "audioName", column = @Column(name = "audio")),
			@AttributeOverride(name = "audioUrl", column = @Column(name = "audioUrl")),
			@AttributeOverride(name = "danceIntroduction", column = @Column(name = "danceIntroduction")) })
	private Connotation connotation;

	// @OneToMany
	// List<OrderYzw> orders = new ArrayList<>();

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

	public CourseType getCourseType() {
		return courseType;
	}

	public SubCourseType getSubCourseType() {
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

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public void setSubCourseType(SubCourseType subCourseType) {
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

	public Connotation getConnotation() {
		return connotation;
	}

	public void setConnotation(Connotation connotation) {
		this.connotation = connotation;
	}

}
