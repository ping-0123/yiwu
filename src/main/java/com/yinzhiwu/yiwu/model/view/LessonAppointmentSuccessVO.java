package com.yinzhiwu.yiwu.model.view;

import java.sql.Time;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.SubCourseType;
import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw.AppointStatus;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.service.OrderYzwService;
import com.yinzhiwu.yiwu.util.SpringUtils;
import com.yinzhiwu.yiwu.util.beanutils.AbstractConverter;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

import io.swagger.annotations.ApiModel;

/**
*@Author ping
*@Time  创建时间:2017年10月28日下午5:12:36
*
*/

@MapedClass(LessonAppointmentYzw.class)
@ApiModel("课时预约详情")
public class LessonAppointmentSuccessVO {
	
	@MapedProperty(ignored=true)
	private Integer times;
	
	@MapedProperty(ignored =true)
	private Float exp;

	@MapedProperty("lesson.store.officialAddress.city")
	private String city;
	
	@MapedProperty("lesson.store.name")
	private String storeName;	
	
	@MapedProperty("lesson.name")
	private String lessonName;
	
	@MapedProperty("lesson.course.dance.name")
	private String danceName;
	
	@MapedProperty("lesson.subCourseType")
	private SubCourseType subCourseType;
	
	@MapedProperty("lesson.dueTeacher.name")
	private String coachName;
	
	@MapedProperty("lesson.lessonDate")
	@JsonFormat(pattern = "yyyy年MM月dd日",timezone="GMT+8")
	private Date lessonDate;
	
	@MapedProperty("lesson.startTime")
	@JsonFormat(pattern = "HH:mm")
	private Time startTime;
	
	@MapedProperty("lesson.endTime")
	@JsonFormat(pattern = "HH:mm")
	private Time endTime;
	
	// 合约信息
	@MapedProperty("contractNo")
	private String contractNo;
	
	@MapedProperty(ignored=true)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date contractStartDate;
	
	@MapedProperty(ignored=true)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date contractEndDate;
	
	@MapedProperty(ignored=true)
	private Integer remainDays;
	
	@MapedProperty(ignored=true)
	private Integer validityTimes;
	
	@MapedProperty(ignored=true)
	private Integer remainTimes;
	
	@MapedProperty(ignored=true)
	private Integer withHoldTimes;

	public static final class LessonAppointmentSuccessVOConverter 
		extends AbstractConverter<LessonAppointmentYzw, LessonAppointmentSuccessVO>{
		
		public static final LessonAppointmentSuccessVOConverter INSTANCE = new LessonAppointmentSuccessVOConverter();
		private final OrderYzwService orderService = SpringUtils.getBean(OrderYzwService.class);
		
		@Override
		public LessonAppointmentSuccessVO fromPO(LessonAppointmentYzw po) {
			LessonAppointmentSuccessVO vo =  super.fromPO(po);
			
			vo.setTimes(1);
			vo.setExp(po.getStatus()==AppointStatus.APPONTED?20f:-20f);
			Contract contract;
			try {
				contract = orderService.findContractByContractNo(vo.getContractNo());
				vo.setContractStartDate(contract.getStart());
				vo.setContractEndDate(contract.getEnd());
				vo.setRemainDays(
						(int) ((contract.getEnd().getTime()-contract.getStart().getTime()) /(1000*60*60*24))
						);
				vo.setValidityTimes(contract.getValidityTimes());
				vo.setRemainTimes(contract.getRemainTimes().intValue());
				vo.setWithHoldTimes(contract.getWithHoldTimes().intValue());
				
			} catch (DataNotFoundException e) {
				;
			}
			
			return vo;
		}
		
		
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

	public SubCourseType getSubCourseType() {
		return subCourseType;
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

	public Integer getRemainDays() {
		return remainDays;
	}

	public Integer getValidityTimes() {
		return validityTimes;
	}

	public Integer getRemainTimes() {
		return remainTimes;
	}

	public Integer getWithHoldTimes() {
		return withHoldTimes;
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

	public void setSubCourseType(SubCourseType subCourseType) {
		this.subCourseType = subCourseType;
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

	public void setRemainDays(Integer remainDays) {
		this.remainDays = remainDays;
	}

	public void setValidityTimes(Integer validityTimes) {
		this.validityTimes = validityTimes;
	}

	public void setRemainTimes(Integer remainTimes) {
		this.remainTimes = remainTimes;
	}

	public void setWithHoldTimes(Integer withHoldTimes) {
		this.withHoldTimes = withHoldTimes;
	}
	
	
}
