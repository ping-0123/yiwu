package com.yinzhiwu.yiwu.model.view;

import java.sql.Time;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Converter;
import com.yinzhiwu.yiwu.entity.LessonPraise;
import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.util.convert.annotation.BeanClass;
import com.yinzhiwu.yiwu.util.convert.annotation.BeanProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
*@Author ping
*@Time  创建时间:2017年10月9日上午11:07:04
*
*/

@BeanClass(LessonYzw.class)
@ApiModel(description="课时VO")
public class LessonVO {
	
	@BeanProperty
	private Integer id;
	
	@BeanProperty
	private String name;
	
	@BeanProperty("course.id")
	private String courseId;
	
	@BeanProperty
	@ApiModelProperty(value="课时的序号， 即第几节课")
	private Integer ordinalNo;
	
	@BeanProperty("lessonDate")
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	@ApiModelProperty(value="课时的上课日期")
	private Date lessonDate;
	
	@BeanProperty
	@ApiModelProperty(value="课时的开始时间")
	private Time startTime;
	
	@BeanProperty
	@ApiModelProperty(value="课时的结束时间")
	private Time endTime;
	
	@BeanProperty("store.id")
	private Integer storeId;
	
	@BeanProperty("store.name")
	@ApiModelProperty(value="上课所在门店名")
	private String storeName;
	
	@BeanProperty(value="store.officialAddress.detailAddress", inverse=false)
	@ApiModelProperty(value="上课所在门店地址")
	private String storeAddress;
	
	@BeanProperty("dueTeacher.id")
	private Integer dueTeacherId;
	
	@BeanProperty("dueTeacher.name")
	@ApiModelProperty(value="排课上课老师姓名")
	private String dueTeacherName;
	
	@BeanProperty("actualTeacher.id")
	private Integer actualTeacherId;
	
	@BeanProperty("actualTeacher.name")
	@ApiModelProperty(value="实际上课老师姓名")
	private String actualTeacherName;
	
	@BeanProperty
	@ApiModelProperty(value="课时内容信息")
	private LessonConnotationVO connotation;
	
	@ApiModelProperty(value="所有点赞人姓名，以逗号分隔")
	private String praisers;
	
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
			StringBuilder praisers = new StringBuilder();
			for (LessonPraise praise : po.getPraises()) {
				if(praise.getDistributer() !=null)
					praisers.append(praise.getDistributer().getName());
			}
			
			if(po.getConnotation() !=null){
				vo.setConnotation(LessonConnotationVO.fromPO(po.getConnotation()));
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
	public String getPraisers() {
		return praisers;
	}
	public void setPraisers(String praisers) {
		this.praisers = praisers;
	}
	public LessonConnotationVO getConnotation() {
		return connotation;
	}
	public void setConnotation(LessonConnotationVO connotation) {
		this.connotation = connotation;
	}

}
