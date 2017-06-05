package com.yinzhiwu.springmvc3.model;

import java.sql.Date;
import java.util.List;

public class LessonList {
	
	private Date date;
	private int weekday;
	private List<LessonOldApiView> list;
	
	public final Date getDate() {
		return date;
	}
	public final void setDate(Date date) {
		this.date = date;
	}
	public final int getWeekday() {
		return weekday;
	}
	public final void setWeekday(int weekday) {
		this.weekday = weekday;
	}
	public final List<LessonOldApiView> getList() {
		return list;
	}
	public final void setList(List<LessonOldApiView> list) {
		this.list = list;
	}
	public LessonList(java.sql.Date date, int weekday, List<LessonOldApiView> list) {
		this.date = date;
		this.weekday = weekday;
		this.list = list;
	}

	
	
}
