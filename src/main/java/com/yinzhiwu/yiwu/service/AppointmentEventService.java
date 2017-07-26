package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.income.AbstractAppointmentEvent;
import com.yinzhiwu.yiwu.model.view.AppointSuccessApiView;

public interface AppointmentEventService extends IBaseService<AbstractAppointmentEvent, Integer> {

	AppointSuccessApiView saveAppoint(int distributerId, int lessonId) throws Exception;

	AppointSuccessApiView saveUnAppoint(int distributerId, int lessonId) throws Exception;

}
