package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.income.AbstractAppointmentEvent;
import com.yinzhiwu.springmvc3.model.view.AppointSuccessApiView;

public interface AppointmentEventService extends IBaseService<AbstractAppointmentEvent,Integer> {

	AppointSuccessApiView saveAppoint(int distributerId, int lessonId) throws Exception;

	AppointSuccessApiView saveUnAppoint(int distributerId, int lessonId) throws Exception;

}
