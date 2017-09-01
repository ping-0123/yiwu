package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.income.AbstractAppointmentEvent;
import com.yinzhiwu.yiwu.model.view.AppointSuccessApiView;

public interface AppointmentEventDao extends IBaseDao<AbstractAppointmentEvent, Integer> {
	
	public AppointSuccessApiView findAppointSuccessApiViewById(int eventId);
}
