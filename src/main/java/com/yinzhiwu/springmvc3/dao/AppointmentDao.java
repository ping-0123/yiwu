package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.model.MiniLesson;

public interface AppointmentDao {

	public int getAppointedStudentCount(int lessonId);
	
	public MiniLesson.AttendedStatus findStatus(int lessonId, int customerId);
}
