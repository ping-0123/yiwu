package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.yzwOld.Appointment;
/**
 * @See AppointmentEventService
 * @author ping
 *
 */

@Deprecated
public interface AppointmentService {

	public boolean appoint(int customer, int lesson);

	public boolean unAppoint(int customerId, int lessonId);

	public Appointment.APPOINT_STATUS getStatus(int customerId, int lessonId);

}
