package com.yinzhiwu.yiwu.model.view;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.enums.SubCourseType;

public class AppointSuccessApiView {

	private Integer times;
	private Float exp;

	// 课程信息
	private String city;
	private String storeName;
	private String lessonName;
	private String danceName;
	private SubCourseType subCourseType;
	private String coachName;
	@JsonFormat(pattern = "yyyy年MM月dd日")
	private Date lessonDate;
	@JsonFormat(pattern = "HH:mm")
	private Time startTime;
	@JsonFormat(pattern = "HH:mm")
	private Time endTime;
	// 合约信息
	private String contractNo;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date contractStartDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date contractEndDate;
	private Integer remainDays;
	private Integer validityTimes;
	private Integer remainTimes;
	private Integer withHoldTimes;

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

	public SubCourseType getSubCourseType() {
		return subCourseType;
	}

	public void setSubCourseType(SubCourseType subCourseType) {
		this.subCourseType = subCourseType;
	}

	public Integer getWithHoldTimes() {
		return withHoldTimes;
	}

	public void setWithHoldTimes(Integer withHoldTimes) {
		this.withHoldTimes = withHoldTimes;
	}

	public AppointSuccessApiView(Float times, Float exp, String city, String storeName, String lessonName,
			String danceName, SubCourseType subCourseType, String coachName, Date lessonDate, Date startTime,
			Date endTime, String contractNo, Date contractStartDate, Date contractEndDate, Integer remainDays,
			Integer validityTimes, BigDecimal remainTimes, Short withHoldTimes) {
		this.times = times.intValue();
		this.exp = exp;
		this.city = city;
		this.storeName = storeName;
		this.lessonName = lessonName;
		this.danceName = danceName;
		this.subCourseType = subCourseType;
		this.coachName = coachName;
		this.lessonDate = lessonDate;
		this.startTime = (Time) startTime;
		this.endTime = (Time) endTime;
		this.contractNo = contractNo;
		this.contractStartDate = contractStartDate;
		this.contractEndDate = contractEndDate;
		this.remainDays = remainDays;
		this.validityTimes = validityTimes;
		this.remainTimes = remainTimes.intValue();
		this.withHoldTimes = withHoldTimes.intValue();
	}

	
}
