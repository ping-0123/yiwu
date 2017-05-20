package com.yinzhiwu.springmvc3.model.view;

import java.sql.Time;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.springmvc3.entity.Lesson;
import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;

public class LessonApiView {

	private int id;
	
	private String name;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@JsonFormat(pattern="HH:mm")
	private Time start;
	
	@JsonFormat(pattern = "HH:mm")
	private Time end;
	
	private String storeName;
	
	private String dueTeacher;
	
	public LessonApiView() {
	}

	public LessonApiView(LessonYzw l){
		this.id = l.getId();
		this.name= l.getName();
		this.date = l.getLessonDate();
		this.start = l.getStartTime();
		this.end = l.getEndTime();
		this.storeName = l.getStoreName();
		this.dueTeacher =l.getDueTeacherName();
	}
	
	public int getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public Time getStart() {
		return start;
	}

	public Time getEnd() {
		return end;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getDueTeacher() {
		return dueTeacher;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setStart(Time start) {
		this.start = start;
	}

	public void setEnd(Time end) {
		this.end = end;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setDueTeacher(String dueTeacher) {
		this.dueTeacher = dueTeacher;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}