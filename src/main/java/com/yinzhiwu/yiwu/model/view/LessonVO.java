package com.yinzhiwu.yiwu.model.view;

import java.sql.Time;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Converter;
import com.yinzhiwu.yiwu.entity.yzw.Connotation;
import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;

/**
*@Author ping
*@Time  创建时间:2017年10月9日上午11:07:04
*
*/

public class LessonVO {
	
	private Integer id;
	private String name;
	private String courseId;
	private Integer ordinalNo;
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date lessonDate;
	private Time startTime;
	private Time endTime;
	private Integer storeId;
	private String storeName;
	private String storeAddress;
	private Integer dueTeacherId;
	private String dueTeacherName;
	private Integer actualTeacherId;
	private String actualTeacherName;
	private Connotation connotation;
	
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getCourseId() {
		return courseId;
	}
	public Integer getOrdinalNo() {
		return ordinalNo;
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
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public void setOrdinalNo(Integer ordinalNo) {
		this.ordinalNo = ordinalNo;
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
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	
	public static LessonVO fromDAO(LessonYzw po){
		return VOConverter.instance.reverse().convert(po);
	}
	
	public static LessonYzw toDAO(LessonVO vo){
		return VOConverter.instance.convert(vo);
	}
	
	private static class VOConverter extends Converter<LessonVO, LessonYzw>{

		public static final VOConverter instance = new VOConverter();
		
		@Override
		protected LessonYzw doForward(LessonVO vo) {
			LessonYzw po = new LessonYzw();
			BeanUtils.copyProperties(vo, po);
			return po;
		}

		@Override
		protected LessonVO doBackward(LessonYzw po) {
			LessonVO vo = new LessonVO();
			BeanUtils.copyProperties(po, vo);
			if(po.getStore() !=null){
				DepartmentYzw s = po.getStore();
				vo.setStoreId(s.getId());
				vo.setStoreName(s.getName());
				vo.setStoreAddress(s.getOfficialAddress()!=null?s.getOfficialAddress().getDetailAddress():null);
			}
			if(po.getActualTeacher() != null){
				EmployeeYzw e = po.getActualTeacher();
				vo.setActualTeacherId(e.getId());
				vo.setActualTeacherName(e.getName());
			}
			if(po.getDueTeacher() !=null){
				EmployeeYzw e = po.getDueTeacher();
				vo.setDueTeacherId(e.getId());
				vo.setDueTeacherName(e.getName());
			}
			
			return vo;
		}
		
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
	public Connotation getConnotation() {
		return connotation;
	}
	public void setConnotation(Connotation connotation) {
		this.connotation = connotation;
	}

}
