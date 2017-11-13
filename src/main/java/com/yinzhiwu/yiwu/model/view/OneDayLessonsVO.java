package com.yinzhiwu.yiwu.model.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
*@Author ping
*@Time  创建时间:2017年8月1日下午3:49:23
*
*/

public class OneDayLessonsVO {

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date date;

	private int weekday;
	private List<LessonForWeeklyVO> list  = new ArrayList<>();
	
	public OneDayLessonsVO(Date date, int weekday, List<LessonForWeeklyVO> list) {
		super();
		this.date = date;
		this.weekday = weekday;
		this.list = list;
	}
	public OneDayLessonsVO() {}
	
	public OneDayLessonsVO(Date time) {
		this.date = time;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		this.weekday = calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	
	public Date getDate() {
		return date;
	}
	public int getWeekday() {
		return weekday;
	}
	public List<LessonForWeeklyVO> getList() {
		return list;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setWeekday(int weekday) {
		this.weekday = weekday;
	}
	public void setList(List<LessonForWeeklyVO> list) {
		this.list = list;
	}
	
	
	
}
