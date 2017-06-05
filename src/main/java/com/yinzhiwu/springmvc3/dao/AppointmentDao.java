package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.Appointment;
import com.yinzhiwu.springmvc3.model.LessonApiView;

public interface AppointmentDao extends IBaseDao<Appointment, Integer>{

	public int getAppointedStudentCount(int lessonId);
	
	public LessonApiView.AttendedStatus findStatus(int lessonId, int customerId);
	
}
