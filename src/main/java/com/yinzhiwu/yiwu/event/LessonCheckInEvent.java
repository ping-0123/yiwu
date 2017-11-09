package com.yinzhiwu.yiwu.event;

import com.yinzhiwu.yiwu.entity.yzw.LessonCheckInYzw;

/**
*@Author ping
*@Time  创建时间:2017年11月9日下午3:11:38
*
*/

@SuppressWarnings("serial")
public class LessonCheckInEvent extends LessonInteractiveEvent implements IncomeEvent{

	public LessonCheckInEvent(LessonCheckInYzw checkin) {
		super(checkin);
	}

	@Override
	public IncomeEventType getType() {
		if( ((LessonCheckInYzw)getSource()).getAppointed() )
			return IncomeEventType.CHECK_IN_AFTER_APPOINTMENT;
		else
			return IncomeEventType.CHECK_IN_WITHOUT_APPOINTMENT;
	}

	@Override
	public String getSourceId() {
		return ((LessonCheckInYzw)getSource()).getId().toString();
	}

	@Override
	public Object getSubject() {
		return ((LessonCheckInYzw)getSource()).getDistributer();
	}

	@Override
	public Float getValue() {
		return 1f;
	}

}
