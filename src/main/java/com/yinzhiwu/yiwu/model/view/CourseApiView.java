package com.yinzhiwu.yiwu.model.view;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Converter;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;

public class CourseApiView {

	private String id;

	private String danceName;

	private String danceGrade;

	private String courseDesc;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	public CourseApiView() {
	};

	public CourseApiView(CourseYzw c) {
		this.id = c.getId();
		this.danceName = c.getDance().getName();
		this.danceGrade = c.getDanceGrade();
		this.courseDesc = c.getDanceDesc();
	}

	public String getId() {
		return id;
	}

	public String getDanceName() {
		return danceName;
	}

	public String getDanceGrade() {
		return danceGrade;
	}

	public String getCourseDesc() {
		return courseDesc;
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

	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
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
	
	public static CourseApiView fromDAO(CourseYzw dao){
		return DTOCoverter.instance.reverse().convert(dao);
	}
	
	public static CourseYzw toDAO(CourseApiView dto){
		return DTOCoverter.instance.convert(dto);
	}
	
	private static class DTOCoverter extends Converter<CourseApiView, CourseYzw>{

		public static final DTOCoverter instance = new DTOCoverter();
		
		@Override
		protected CourseYzw doForward(CourseApiView a) {
			CourseYzw course = new CourseYzw();
			BeanUtils.copyProperties(a, course);
			return course;
		}

		@Override
		protected CourseApiView doBackward(CourseYzw b) {
			CourseApiView v = new CourseApiView();
			BeanUtils.copyProperties(b, v);
			return v;
		}
		
	}
}
