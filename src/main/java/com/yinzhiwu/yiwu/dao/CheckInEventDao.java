package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.income.CheckInEvent;
import com.yinzhiwu.yiwu.model.view.LessonCheckInSuccessApiView;

public interface CheckInEventDao extends IBaseDao<CheckInEvent, Integer> {
	
	public LessonCheckInSuccessApiView findCheckInSuccessApiViewById(int eventId);
}
