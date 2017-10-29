package com.yinzhiwu.yiwu.model.view;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.LessonCheckInYzw;
import com.yinzhiwu.yiwu.util.beanutils.AbstractConverter;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author ping
 * @Date 2017年10月29日 下午6:31:48
 *
 */

@MapedClass(LessonCheckInYzw.class)
@ApiModel("签到成功之后的返回对象")
public class LessonCheckInSuccessApiView {

	@ApiModelProperty("消耗会籍合约的次数")
	@MapedProperty(ignored=true)
	private Integer times;
	
	@ApiModelProperty("经验值")
	@MapedProperty(ignored=true)
	private Float exp;

	// 课程信息
	@ApiModelProperty("上课门店所在城市")
	@MapedProperty("lesson.store.officialAddress.city")
	private String city;
	
	@ApiModelProperty("门店")
	@MapedProperty("lesson.store.name")
	private String storeName;
	
	@ApiModelProperty("课时名")
	@MapedProperty("lesson.name")
	private String lessonName;
	
	@ApiModelProperty("舞蹈名")
	@MapedProperty("lesson.course.dance.name")
	private String danceName;
	
	@ApiModelProperty(value="课时类型",allowableValues="{CLOSED, OPENED, PRIVATE}")
	@MapedProperty("lesson.courseType")
	private CourseType courseType;
	
	@ApiModelProperty(value="上课老师姓名，指排课老师")
	@MapedProperty("lesson.dueTeacher.name")
	private String coachName;
	
	@ApiModelProperty("上课日期")
	@JsonFormat(pattern = "yyyy年MM月dd日", timezone="GMT+8")
	@MapedProperty("lesson.lessonDate")
	private Date lessonDate;
	
	@ApiModelProperty("上课开始时间")
	@MapedProperty("lesson.startTime")
	@JsonFormat(pattern = "HH:mm")
	private Time startTime;
	
	@ApiModelProperty("上课结束时间")
	@MapedProperty("lesson.endTime")
	@JsonFormat(pattern = "HH:mm")
	private Time endTime;
	
	
	// 合约信息
	@ApiModelProperty("会籍合约编号")
	@MapedProperty("contractNo")
	private String contractNo;
	
	@ApiModelProperty("会籍合约开始日期")
	@MapedProperty(ignored=true)
	private Date contractStartDate;
	
	@ApiModelProperty("会籍合约结束日期")
	@MapedProperty(ignored=true)
	private Date contractEndDate;
	
	@ApiModelProperty("会籍合约有效次数")
	@MapedProperty(ignored=true)
	private Integer validityTimes;
	
	@ApiModelProperty("会籍合约剩余次数")
	@MapedProperty(ignored=true)
	private Integer remainTimes;
	
	@ApiModelProperty("会籍合约待扣次数")
	@MapedProperty(ignored=true)
	private Integer withHoldTimes;
	
	public static final class LessonCheckInSuccessApiViewConverter extends AbstractConverter<LessonCheckInYzw, LessonCheckInSuccessApiView>{
		public static final LessonCheckInSuccessApiViewConverter INSTANCE = new LessonCheckInSuccessApiViewConverter();
	}
	
	public LessonCheckInSuccessApiView() {
	}


	public Integer getTimes() {
		return times;
	}

	public Float getExp() {
		return exp;
	}

	public String getCity() {
		return city;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getCoachName() {
		return coachName;
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

	public String getContractNo() {
		return contractNo;
	}

	public Integer getValidityTimes() {
		return validityTimes;
	}

	public Integer getRemainTimes() {
		return remainTimes;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public void setExp(Float exp) {
		this.exp = exp;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setCoachName(String coachName) {
		this.coachName = coachName;
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

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public void setValidityTimes(Integer validityTimes) {
		this.validityTimes = validityTimes;
	}

	public void setRemainTimes(Integer remainTimes) {
		this.remainTimes = remainTimes;
	}

	public String getLessonName() {
		return lessonName;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}


	public String getDanceName() {
		return danceName;
	}

	public void setDanceName(String danceName) {
		this.danceName = danceName;
	}

	public Integer getWithHoldTimes() {
		return withHoldTimes;
	}

	public void setWithHoldTimes(Integer withHoldTimes) {
		this.withHoldTimes = withHoldTimes;
	}

	public LessonCheckInSuccessApiView(Float times, Float exp, String city, String storeName, String lessonName,
			String danceName, CourseType courseType, String coachName, Date lessonDate, Date startTime, Date endTime,
			String contractNo, Date contractStartDate, Date contractEndDate, Integer validityTimes, BigDecimal remainTimes,
			Short withHoldTimes) {
		this.times = times.intValue();
		this.exp = exp;
		this.city = city;
		this.storeName = storeName;
		this.lessonName = lessonName;
		this.danceName = danceName;
		this.courseType = courseType;
		this.coachName = coachName;
		this.lessonDate = lessonDate;
		this.startTime = (Time) startTime;
		this.endTime = (Time) endTime;
		this.contractNo = contractNo;
		this.contractStartDate = contractStartDate;
		this.contractEndDate = contractEndDate;
		this.validityTimes = validityTimes;
		this.remainTimes = remainTimes.intValue();
		this.withHoldTimes = withHoldTimes.intValue();
	}

	
}
