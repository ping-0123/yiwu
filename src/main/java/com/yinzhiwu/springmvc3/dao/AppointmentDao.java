package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.Appointment;
import com.yinzhiwu.springmvc3.model.LessonOldApiView;

public interface AppointmentDao extends IBaseDao<Appointment, Integer>{

	public int getAppointedStudentCount(int lessonId);
	
	public LessonOldApiView.AttendedStatus findStatus(int lessonId, int customerId);
	
}
