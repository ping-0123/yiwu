package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.income.CheckInEvent;
import com.yinzhiwu.yiwu.model.view.CheckInSuccessApiView;

public interface CheckInEventDao extends IBaseDao<CheckInEvent, Integer> {
	
	public CheckInSuccessApiView findById(int eventId);
}
