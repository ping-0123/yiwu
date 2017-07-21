package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.yzwOld.Appointment;
import com.yinzhiwu.yiwu.model.LessonOldApiView;

public interface AppointmentDao extends IBaseDao<Appointment, Integer>{

	public int getAppointedStudentCount(int lessonId);
	
	public LessonOldApiView.AttendedStatus findStatus(int lessonId, int customerId);
	
}
