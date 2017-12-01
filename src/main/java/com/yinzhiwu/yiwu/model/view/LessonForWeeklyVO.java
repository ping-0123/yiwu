package com.yinzhiwu.yiwu.model.view;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.context.UserContext;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.LessonInteractive;
import com.yinzhiwu.yiwu.entity.yzw.LessonCheckInYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw.CheckedInStatus;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw.LessonStatus;
import com.yinzhiwu.yiwu.enums.CourseType;
import com.yinzhiwu.yiwu.enums.SubCourseType;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.business.BusinessDataLogicException;
import com.yinzhiwu.yiwu.service.LessonCheckinService;
import com.yinzhiwu.yiwu.service.LessonInteractiveService;
import com.yinzhiwu.yiwu.util.SpringUtils;
import com.yinzhiwu.yiwu.util.beanutils.AbstractConverter;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
*@Author ping
*@Time  创建时间:2017年8月1日下午3:40:04
*
*/

@MapedClass(LessonYzw.class)
@ApiModel(description="显示在周课表里的课时VO")
public class LessonForWeeklyVO {
	
	private static final Logger logger = LoggerFactory.getLogger(LessonForWeeklyVO.class);
	
	private Integer id;
	private String name;
	
	@MapedProperty(value="course.dance.name")
	private String danceName;
	
	@MapedProperty(value="course.danceGrade")
	private String danceGrade;
	
	@MapedProperty(value="course.id")
	private String courseId;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date lessonDate;
	
	@MapedProperty(ignored=true)
	private Integer weekDay;
	private Time startTime;
	private Time endTime;
	
	@MapedProperty(value="store.id")
	private Integer storeId;
	
	@MapedProperty(value="store.name")
	private String storeName;
	//课程时长
	private Float hours;
	
	@MapedProperty(value="dueTeacher.id")
	private Integer dueTeacherId;
	
	@MapedProperty(value="dueTeacher.name")
	private String dueTeacherName;
	
	@MapedProperty(value="actualTeacher.id")
	private Integer actualTeacherId;
	
	@MapedProperty(value="actualTeacher.name")
	private String actualTeacherName;
	private CourseType courseType;
	private SubCourseType subCourseType;
	private LessonStatus lessonStatus;
	
	@MapedProperty(value="classRoom.maxStudentCount")
	@ApiModelProperty(value="可以容纳的最大人数,开放式课程指教室容量, 如果是封闭式课程， 该值为0")
	private Integer maxStudentCount;		//可容纳人数   封闭课程指班级人数， 开放式课程指教室容量
	
	@ApiModelProperty(value="预约人数, 如果是封闭式课程， 该值为0")
	private Integer appointedStudentCount; //预约人数
	
	@MapedProperty(value="actualStudentCount")
	@ApiModelProperty(value="课程班级人数, 如果是开放式课程，该值为0")
	private Integer attendedStudentCount;	//参加人数, 封闭式课的班级人数 
	
	@ApiModelProperty(value="签到人数")
	private Integer checkedInStudentCount; //签到人数
	
	@ApiModelProperty(value="店员点名人数")
	private Integer rollCalledStudentCountByStoreman;  //店员点名人数
	
	@ApiModelProperty(value="教师点名人数")
	private Integer rollCalledStudentCountByCoach;   //教师点名人数
	
	@ApiModelProperty(value="这个课在整套课程中所处的位置")
	private Integer ordinalNo;
	
	@MapedProperty("course.sumLessonTimes")
	@ApiModelProperty(value="这节课对应的课程所拥有的总课次")
	private Integer sumTimesOfCourse;
	
	
	@MapedProperty(ignored=true)
	@ApiModelProperty(value="预约状态",allowableValues="[APPONTED,UN_APOINTED]")
	private Boolean appointed;
	
	@MapedProperty(ignored=true)
	private Boolean checkedIn;
	
	@MapedProperty(ignored=true)
	private CheckedInStatus coachCheckedInStatus;
	
	public static final class LessonForWeeklyVOConverter extends AbstractConverter<LessonYzw, LessonForWeeklyVO>
	{
		public static final LessonForWeeklyVOConverter INSTANCE = new LessonForWeeklyVOConverter();
		private final LessonInteractiveService interactiveService = SpringUtils.getBean(LessonInteractiveService.class);
//		private final LessonCheckinService checkinService = SpringUtils.getBean(LessonCheckinService.class);
//		private final LessonYzwService lessonService = SpringUtils.getBean(LessonYzwService.class);
		private final LessonCheckinService lessonCheckinService = SpringUtils.getBean(LessonCheckinService.class);
		
		@Override
		public LessonForWeeklyVO fromPO(LessonYzw lesson) {
			LessonForWeeklyVO vo =  super.fromPO(lesson);
			vo.setWeekDay();
			Distributer distributer = UserContext.getDistributer();
			if(null != distributer){
				try {
					LessonInteractive li = interactiveService.findByDistributerIdAndLessonId(distributer.getId(), lesson.getId());
					vo.setAppointed(li.getAppointed());
					vo.setCheckedIn(li.getCheckedIn());
				} catch (DataNotFoundException e) {
					vo.setAppointed(false);
					vo.setCheckedIn(false);
				}
			}else{
				//设置 coachCheckedInStatus
				vo.setCoachCheckedInStatus(getCoachCheckedStatus(lesson));
			}
			return vo;
		}

		private CheckedInStatus getCoachCheckedStatus(LessonYzw lesson) {
			if(null != lesson.getActualTeacher()){
				try {
					LessonCheckInYzw checkin = lessonCheckinService.findOneByProperties(
							new String[]{"lesson.id","teacher.id"}, 
							new Object[]{lesson.getId(),lesson.getActualTeacher().getId()});
					
					Date date = lesson.getLessonDate();
					Time start = lesson.getStartTime();
					Time end = lesson.getEndTime();
					
					Calendar lessonStart = Calendar.getInstance();
					lessonStart.setTime(date);
					lessonStart.set(Calendar.HOUR_OF_DAY, start.getHours());
					lessonStart.set(Calendar.MINUTE,start.getMinutes());
					lessonStart.set(Calendar.SECOND, start.getSeconds());
					
					Calendar lessonEnd = Calendar.getInstance();
					lessonEnd.setTime(date);
					lessonEnd.set(Calendar.HOUR_OF_DAY, end.getHours());
					lessonEnd.set(Calendar.MINUTE, end.getMinutes());
					lessonEnd.set(Calendar.SECOND, end.getSeconds());
					
					if(checkin.getCreateTime().before(lessonStart.getTime())){
						return CheckedInStatus.CHECKED;
					}else if (checkin.getCreateTime().before(lessonEnd.getTime())) {
						return CheckedInStatus.LATE;
					}else {
						return CheckedInStatus.PATCHED;
					}
				} catch (DataNotFoundException e) {
					String message = "课程#" + lesson.getId()  + "数据异常 教师未签到，却已记录课时";
					logger.error(message);
					throw new BusinessDataLogicException(message);
				}
			}else{
				if(LessonStatus.UN_CHECKED ==lesson.getLessonStatus())
					return CheckedInStatus.NON_CHECKABLE;
				else
					return CheckedInStatus.UN_CHECKED;
			}
		}
		
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

	public Integer getAppointedStudentCount() {
		return appointedStudentCount;
	}

	public Integer getAttendedStudentCount() {
		return attendedStudentCount;
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
		this.weekDay = getWeekDay();
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


	public Float getHours() {
		return hours;
	}

	public void setHours(Float hours) {
		this.hours = hours;
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


	public void setAppointedStudentCount(Integer appointedStudentCount) {
		this.appointedStudentCount = appointedStudentCount;
	}

	public void setAttendedStudentCount(Integer attendedStudentCount) {
		this.attendedStudentCount = attendedStudentCount;
	}


	public void setSumTimesOfCourse(Integer sumTimesOfCourse) {
		this.sumTimesOfCourse = sumTimesOfCourse;
	}

	
	public Integer getWeekDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(lessonDate);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	public Integer getCheckedInStudentCount() {
		return checkedInStudentCount;
	}
	public void setCheckedInStudentCount(Integer checkedInStudentCount) {
		this.checkedInStudentCount = checkedInStudentCount;
	}
	public Integer getRollCalledStudentCountByStoreman() {
		return rollCalledStudentCountByStoreman;
	}
	public Integer getRollCalledStudentCountByCoach() {
		return rollCalledStudentCountByCoach;
	}
	public Integer getOrdinalNo() {
		return ordinalNo;
	}
	public void setWeekDay() {
		this.weekDay = getWeekDay();
	}
	public void setRollCalledStudentCountByStoreman(Integer rollCalledStudentCountByStoreman) {
		this.rollCalledStudentCountByStoreman = rollCalledStudentCountByStoreman;
	}
	public void setRollCalledStudentCountByCoach(Integer rollCalledStudentCountByCoach) {
		this.rollCalledStudentCountByCoach = rollCalledStudentCountByCoach;
	}
	public void setOrdinalNo(Integer ordinalNo) {
		this.ordinalNo = ordinalNo;
	}

	public CheckedInStatus getCoachCheckedInStatus() {
		return coachCheckedInStatus;
	}

	public void setCoachCheckedInStatus(CheckedInStatus coachCheckedInStatus) {
		this.coachCheckedInStatus = coachCheckedInStatus;
	}

	public Boolean getAppointed() {
		return appointed;
	}

	public Boolean getCheckedIn() {
		return checkedIn;
	}

	public void setAppointed(Boolean appointed) {
		this.appointed = appointed;
	}

	public void setCheckedIn(Boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	
}
