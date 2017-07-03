package com.yinzhiwu.springmvc3.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LessonList {
	

	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date date;
	
	private int weekday;
	
	private List<LessonOldApiView> list;
	
	
	public LessonList(Date date, int weekday, List<LessonOldApiView> list) {
		this.date = date;
		this.weekday = weekday;
		this.list = list;
	}

	public Date getDate() {
		return date;
	}


	public int getWeekday() {
		return weekday;
	}


	public List<LessonOldApiView> getList() {
		return list;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public void setWeekday(int weekday) {
		this.weekday = weekday;
	}


	public void setList(List<LessonOldApiView> list) {
		this.list = list;
	}

	
	
}
