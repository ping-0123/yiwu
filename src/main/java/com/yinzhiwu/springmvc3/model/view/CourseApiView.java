package com.yinzhiwu.springmvc3.model.view;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.springmvc3.entity.yzw.CourseYzw;

public class CourseApiView {
	
	private String id;
	
	private String danceName;
	
	private String danceGrade;
	
	private String courseDesc;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date start;
	
	public CourseApiView(){};
	
	public CourseApiView(CourseYzw c){
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

	public Date getStart() {
		return start;
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

	public void setStart(Date start) {
		this.start = start;
	}
	
	
}
