package com.yinzhiwu.yiwu.model.view;

import java.sql.Time;
import java.util.Date;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.income.CheckInEvent;
import com.yinzhiwu.yiwu.entity.income.IncomeRecord;
import com.yinzhiwu.yiwu.entity.type.IncomeType;
import com.yinzhiwu.yiwu.entity.yzw.CheckInsYzw;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;

public class CheckInSuccessApiView {

	private Integer times;
	private Float exp;

	// 课程信息
	private String city;
	private String storeName;
	private String lessonName;
	private String danceName;
	private String courseType;
	private String coachName;
	@JsonFormat(pattern = "yyyy年MM月dd日")
	private Date lessonDate;
	@JsonFormat(pattern = "HH:mm")
	private Time startTime;
	@JsonFormat(pattern = "HH:mm")
	private Time endTime;
	// 合约信息
	private String contractNo;
	private Date contractStartDate;
	private Date contractEndDate;
	private Integer validityTimes;
	private Integer remainTimes;

	public CheckInSuccessApiView() {
		super();
	}

	public CheckInSuccessApiView(CheckInEvent event, Contract contract) {
		Assert.notNull(event);
		Assert.notNull(event.getCheckIn());
		Assert.notNull(event.getCheckIn().getLesson());
		Assert.notNull(contract);
		Assert.isTrue(event.getCheckIn().getContractNo().equals(contract.getContractNo()));

		CheckInsYzw checkIn = event.getCheckIn();
		this.times = (int) event.getParam().floatValue();
		IncomeRecord record = event.getAppointedIncomeRecord(IncomeType.EXP);
		if (record != null)
			exp = record.getIncomeValue();
		LessonYzw lesson = checkIn.getLesson();
		DepartmentYzw store = lesson.getStore();
		if (store != null) {
			this.storeName = store.getName();
			this.city = store.getCity();
		}
		this.lessonName = lesson.getName();
		if (lesson.getCourse() != null)
			this.danceName = lesson.getCourse().getDanceDesc();
		this.courseType = lesson.getCourseType();
		this.coachName = lesson.getDueTeacherName();
		this.lessonDate = lesson.getLessonDate();
		this.startTime = lesson.getStartTime();
		this.endTime = lesson.getEndTime();
		this.contractNo = contract.getContractNo();
		this.contractStartDate = contract.getStart();
		this.contractEndDate = contract.getEnd();
		this.validityTimes = contract.getValidityTimes();
		this.remainTimes = (int) contract.getRemainTimes();
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

	public String getCourseType() {
		return courseType;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public void setCourseType(String courseType) {
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

	public CheckInSuccessApiView(Integer times, Float exp, String city, String storeName, String lessonName,
			String courseType, String coachName, Date lessonDate, Time startTime, Time endTime, String contractNo,
			Date contractStartDate, Date contractEndDate, Integer validityTimes, Integer remainTimes) {
		super();
		this.times = times;
		this.exp = exp;
		this.city = city;
		this.storeName = storeName;
		this.lessonName = lessonName;
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

	public String getDanceName() {
		return danceName;
	}

	public void setDanceName(String danceName) {
		this.danceName = danceName;
	}

}
