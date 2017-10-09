package com.yinzhiwu.yiwu.model.view;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Converter;
import com.yinzhiwu.yiwu.entity.yzw.Connotation;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseStatus;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.service.LessonYzwService;
import com.yinzhiwu.yiwu.util.SpringUtils;

public class CourseVO {

	private String id;
	private String name;
	private String danceId;
	private String danceName;
	private String danceGrade;
	
	private Integer storeId;
	private Integer teacherId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	private CourseType courseType;
	private CourseStatus courseStatus;
	private Integer studentCount;
	private Integer sumLessonTimes;
	private Connotation connotation;
	private boolean comming;
	
	public CourseVO() {
	};

	public String getId() {
		return id;
	}

	public String getDanceName() {
		return danceName;
	}

	public String getDanceGrade() {
		return danceGrade;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDanceName(String danceName) {
		this.danceName = danceName;
	}

	public void setDanceGrade(String danceGrade) {
		this.danceGrade = danceGrade;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public static CourseVO fromDAO(CourseYzw dao){
		return DTOCoverter.instance.reverse().convert(dao);
	}
	
	public static CourseYzw toDAO(CourseVO dto){
		return DTOCoverter.instance.convert(dto);
	}
	
	private static class DTOCoverter extends Converter<CourseVO, CourseYzw>{

		public static final DTOCoverter instance = new DTOCoverter();
		
		@Override
		protected CourseYzw doForward(CourseVO a) {
			CourseYzw course = new CourseYzw();
			BeanUtils.copyProperties(a, course);
			return course;
		}

		@Override
		protected CourseVO doBackward(CourseYzw c) {
			CourseVO v = new CourseVO();
			BeanUtils.copyProperties(c, v);
			LessonYzwService service = SpringUtils.getBean(LessonYzwService.class);
			v.setComming(service.findComingLessonByCourseId(c.getId())!=null);
			if(c.getDance() != null){
				v.setDanceId(c.getDance().getId());
				v.setDanceName(c.getDance().getName());}
			v.setStoreId(c.getStore()==null?null:c.getStore().getId());
			v.setTeacherId(c.getTeacher()==null?null:c.getTeacher().getId());
			return v;
		}
		
	}

	public boolean isComming() {
		return comming;
	}

	public void setComming(boolean comming) {
		this.comming = comming;
	}

	public String getName() {
		return name;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public CourseStatus getCourseStatus() {
		return courseStatus;
	}

	public Integer getStudentCount() {
		return studentCount;
	}

	public Connotation getConnotation() {
		return connotation;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public void setCourseStatus(CourseStatus courseStatus) {
		this.courseStatus = courseStatus;
	}

	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}

	public void setConnotation(Connotation connotation) {
		this.connotation = connotation;
	}

	public String getDanceId() {
		return danceId;
	}

	public void setDanceId(String danceId) {
		this.danceId = danceId;
	}

	public Integer getSumLessonTimes() {
		return sumLessonTimes;
	}

	public void setSumLessonTimes(Integer sumLessonTimes) {
		this.sumLessonTimes = sumLessonTimes;
	}

}
