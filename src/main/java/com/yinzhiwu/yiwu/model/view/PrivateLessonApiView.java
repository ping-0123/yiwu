package com.yinzhiwu.yiwu.model.view;

import java.sql.Time;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
*@Author ping
*@Time  创建时间:2017年8月16日下午8:44:19
*
*/

public class PrivateLessonApiView {
	
	private int id;
	private String name;
	private String teacherName;
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date date;
	@JsonFormat(pattern="HH:mm")
	private Time start;
	@JsonFormat(pattern="HH:mm")
	private Time end;
	private int praises;
	private int comments;
	
	public PrivateLessonApiView() {
	}

	public PrivateLessonApiView(int id, String name, String teacherName, Date date, Date start, Date end) {
		super();
		this.id = id;
		this.name = name;
		this.teacherName = teacherName;
		this.date = date;
		this.start = (Time) start;
		this.end = (Time) end;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getTeacherName() {
		return teacherName;
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

	public int getPraises() {
		return praises;
	}

	public int getComments() {
		return comments;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
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

	public void setPraises(int praises) {
		this.praises = praises;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}
	
	
}
