package com.yinzhiwu.yiwu.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.model.view.LessonForWeeklyDto;

/**
*@Author ping
*@Time  创建时间:2017年8月1日下午3:49:23
*
*/

public class DailyLessonsDto {

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date date;

	private int weekday;
	private List<LessonForWeeklyDto> list;
	public Date getDate() {
		return date;
	}
	public int getWeekday() {
		return weekday;
	}
	public List<LessonForWeeklyDto> getList() {
		return list;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setWeekday(int weekday) {
		this.weekday = weekday;
	}
	public void setList(List<LessonForWeeklyDto> list) {
		this.list = list;
	}
	
	public DailyLessonsDto(Date date, int weekday, List<LessonForWeeklyDto> list) {
		super();
		this.date = date;
		this.weekday = weekday;
		this.list = list;
	}
	public DailyLessonsDto() {}
	
	
}