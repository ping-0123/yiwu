package com.yinzhiwu.springmvc3.model.view;

import java.sql.Time;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.springmvc3.entity.income.AbstractAppointmentEvent;
import com.yinzhiwu.springmvc3.entity.yzw.Contract;
import com.yinzhiwu.springmvc3.entity.yzw.DepartmentYzw;
import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;

public class AppointSuccessApiView {

	private Integer times;
	private Float exp;
	
	//课程信息
	private String city;
	private String storeName;
	private String lessonName;
	private String danceName;
	private String courseType;
	private String coachName;
	@JsonFormat(pattern="yyyy年MM月dd日")
	private Date lessonDate;
	@JsonFormat(pattern="HH:mm")
	private Time startTime;
	@JsonFormat(pattern="HH:mm")
	private Time endTime;
	//合约信息
	private String contractNo;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date contractStartDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date contractEndDate;
	private Integer remainDays;
	private Integer validityTimes;
	private Integer remainTimes;
	
	public AppointSuccessApiView(AbstractAppointmentEvent event, Contract contract, float exp){
		if(event != null && contract != null){
			this.times = (int) event.getParam().floatValue();
			this.exp = exp;
			LessonYzw lesson = event.getLesson();
			if(lesson !=null){
				DepartmentYzw store = lesson.getStore();
				if(store != null){
					this.city =store.getCity();
					this.storeName = store.getName();
				}
				this.lessonName = lesson.getName();
				if(lesson.getCourse() != null)
					this.danceName = lesson.getCourse().getDanceDesc();
				this.courseType = lesson.getSubCourseType();
				this.coachName = lesson.getDueTeacherName();
				this.lessonDate = lesson.getLessonDate();
				this.startTime = lesson.getStartTime();
				this.endTime = lesson.getEndTime();
				this.contractNo = contract.getContractNo();
				this.contractStartDate = contract.getStart();
				this.contractEndDate  = contract.getEnd();
				this.remainDays = (int) ((this.contractEndDate.getTime()-System.currentTimeMillis())/(24*1000*3600));
				this.validityTimes = contract.getValidityTimes();
				this.remainTimes = (int) contract.getRemainTimes();
			}
		}
	}
	public AppointSuccessApiView(Integer times, Float exp, String city, String storeName, String lessonName,
			String danceName, String courseType, String coachName, Date lessonDate, Time startTime, Time endTime,
			String contractNo, Date contractStartDate, Date contractEndDate, Integer validityTimes,
			Integer remainTimes) {
		super();
		this.times = times;
		this.exp = exp;
		this.city = city;
		this.storeName = storeName;
		this.lessonName = lessonName;
		this.danceName = danceName;
		this.courseType = courseType;
		this.coachName = coachName;
		this.lessonDate = lessonDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.contractNo = contractNo;
		this.contractStartDate = contractStartDate;
		this.contractEndDate = contractEndDate;
		this.validityTimes = validityTimes;
		this.remainTimes = remainTimes;
	}

	public AppointSuccessApiView() {
		super();
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

	public String getLessonName() {
		return lessonName;
	}

	public String getDanceName() {
		return danceName;
	}

	public String getCourseType() {
		return courseType;
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

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public Date getContractEndDate() {
		return contractEndDate;
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

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public void setDanceName(String danceName) {
		this.danceName = danceName;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
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

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public void setValidityTimes(Integer validityTimes) {
		this.validityTimes = validityTimes;
	}

	public void setRemainTimes(Integer remainTimes) {
		this.remainTimes = remainTimes;
	}
	public Integer getRemainDays() {
		return remainDays;
	}
	public void setRemainDays(Integer remainDays) {
		this.remainDays = remainDays;
	}

	
	
	
	
}
