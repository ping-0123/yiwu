package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.Appointment;

public interface AppointmentService {

	public boolean appoint(int customer, int lesson);

	public boolean unAppoint(int customerId, int lessonId);

	public Appointment.APPOINT_STATUS getStatus(int customerId, int lessonId);

}
